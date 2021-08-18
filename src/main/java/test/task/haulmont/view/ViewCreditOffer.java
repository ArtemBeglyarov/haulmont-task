package test.task.haulmont.view;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import test.task.haulmont.entity.Client;
import test.task.haulmont.entity.Credit;
import test.task.haulmont.entity.CreditOffer;
import test.task.haulmont.entity.PaymentSchedule;
import test.task.haulmont.operations.*;

import javax.annotation.PostConstruct;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringView(name = "CreditOffers")
public class ViewCreditOffer extends VerticalLayout implements View {
    @Autowired
    private ClientOperations clientOperations;
    @Autowired
    private CreditOperations creditOperations;
    @Autowired
    private BankOperations bankOperations;
    @Autowired
    private CreditOfferOperations creditOfferOperations;
    @Autowired
    private PaymentOperations paymentOperations;
    private Button confirm;
    TextField amount;
    TextField creditTerm;
    NativeSelect<Client> clientNativeSelect;
    NativeSelect<Credit> creditNativeSelect;

    VerticalLayout vertical = new VerticalLayout();
    HorizontalLayout horizontalLayout = new HorizontalLayout();

    @PostConstruct
    void init() {

        addComponent(vertical);
        horizontalLayout.addComponent(amount = new TextField("amount of credit"));
        horizontalLayout.addComponent(creditTerm = new TextField("number of months"));
        amount.setIcon(VaadinIcons.WALLET);
        creditTerm.setIcon(VaadinIcons.CALENDAR);
        vertical.addComponent(horizontalLayout);
        vertical.addComponent(confirm = new Button("confirm", event -> getUI().addWindow(selectCreditAndClient(Double.parseDouble(amount.getValue()), Double.parseDouble(creditTerm.getValue())))));
    }

    private Window selectCreditAndClient(Double amount, Double creditTerm) {
        CreditOffer creditOffer = new CreditOffer();

        clientNativeSelect = new NativeSelect<>("Select client", clientOperations.getAll());
        clientNativeSelect.setItemCaptionGenerator(Client::getName);
        creditNativeSelect = new NativeSelect<>("Select credit", creditOperations.getAll());
        creditNativeSelect.setItemCaptionGenerator(Credit::getName);

        Window window = new Window("Credit offer");
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        VerticalLayout vertical = new VerticalLayout();

        vertical.addComponent(clientNativeSelect);
        vertical.addComponent(creditNativeSelect);
        vertical.addComponent(horizontalLayout);

        horizontalLayout.addComponent(new Button("ok", event -> {
            creditOffer.setClient(clientNativeSelect.getValue());
            creditOffer.setCredit(creditNativeSelect.getValue());
            creditOffer.setCreditAmount(amount.doubleValue());
            creditOfferOperations.create(creditOffer);
            creditOffer.setPaymentSchedule(createPaymentSchedule(creditOffer, creditTerm.doubleValue()));
            window.close();
            getUI().getNavigator().navigateTo("PaymentSchedule");

        }));
        horizontalLayout.addComponent(new Button("close", event -> window.close()));

        window.setContent(vertical);
        window.center();
        window.setWidth("20%");
        window.setModal(true);
        return window;
    }

    private List<PaymentSchedule> createPaymentSchedule(CreditOffer creditOffer, Double creditTerm) {
        List<PaymentSchedule> paymentScheduleList = new ArrayList<>();
        double sum = creditOffer.getCreditAmount();
        double paymentBody = sum / creditTerm;
        double percent = creditOffer.getCredit().getCreditPercent();
        LocalDateTime localDateTime = LocalDateTime.now();
        Date date = Date.valueOf(localDateTime.toLocalDate());
        for (int i = 0; i < creditTerm; i++) {

            PaymentSchedule paymentSchedule = new PaymentSchedule();
            paymentSchedule.setPayDay(date);
            localDateTime = localDateTime.plusMonths(1);
            date = Date.valueOf(localDateTime.toLocalDate());
            paymentSchedule.setPaymentBodyCredit(paymentBody);
            paymentSchedule.setPaymentPercent(sum * percent /100 /12);

            double paymentSum = paymentSchedule.getPaymentPercent() + paymentSchedule.getPaymentBodyCredit();
            paymentSchedule.setPaymentSum(paymentSum);

            paymentScheduleList.add(paymentSchedule);
            sum = sum -paymentBody;
            paymentSchedule.setCreditOffer(creditOffer);
            paymentOperations.create(paymentSchedule);
        }


        return paymentScheduleList;
    }
}
