package test.task.haulmont.view;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import test.task.haulmont.entity.Client;
import test.task.haulmont.entity.Credit;
import test.task.haulmont.entity.CreditOffer;
import test.task.haulmont.entity.PaymentSchedule;
import test.task.haulmont.operations.ClientOperations;
import test.task.haulmont.operations.CreditOfferOperations;
import test.task.haulmont.operations.PaymentOperations;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringView(name = "PaymentSchedule")
public class ViewPayment extends VerticalLayout implements View {

    Grid<PaymentSchedule> paymentScheduleGrid;

    @Autowired
    private ClientOperations clientOperations;

    @Autowired
    CreditOfferOperations creditOfferOperations;
    @Autowired
    PaymentOperations paymentOperations;

    private List<CreditOffer> creditOffers;
    Grid<Client> grid;
    Grid<CreditOffer> creditOfferGrid;

    VerticalLayout vertical = new VerticalLayout();
    HorizontalLayout horizontalLayout = new HorizontalLayout();
    Button viewPay;
    Button remove;

    @PostConstruct
    void init() {
        addComponent(horizontalLayout);
        showAllClient();
       horizontalLayout.addComponent(viewPay=new Button("View pay",event -> getUI().addWindow(viewPaymentSchedule(creditOffers.get(0)))));
       horizontalLayout.addComponent(remove=new Button("Remove",event ->{creditOfferOperations.deleteAll(creditOffers);getUI().getNavigator().navigateTo("PaymentSchedule");}));
        viewPay.setEnabled(false);
        remove.setEnabled(false);
    }

    private Window viewPaymentSchedule(CreditOffer creditOffer) {
        Window window = new Window();
        paymentScheduleGrid = new Grid<>(PaymentSchedule.class);
        paymentScheduleGrid.setItems(creditOffers.get(0).getPaymentSchedule());
        paymentScheduleGrid.removeAllColumns();
        paymentScheduleGrid.setWidth("1400");
        paymentScheduleGrid.addColumn(PaymentSchedule::getPayDay).setCaption("Day payment");
        paymentScheduleGrid.addColumn(PaymentSchedule::getPaymentSum).setCaption("Payment sum");
        paymentScheduleGrid.addColumn(PaymentSchedule::getPaymentBodyCredit).setCaption("Payment body credit");
        paymentScheduleGrid.addColumn(PaymentSchedule::getPaymentPercent).setCaption("Pay percent");
        window.setContent(paymentScheduleGrid);
        window.setModal(true);
        window.center();
        return window;
    }

    private void showAllClient() {
        creditOfferGrid = new Grid<>(CreditOffer.class);
        creditOfferGrid.setItems(creditOfferOperations.getAll());
        creditOfferGrid.removeAllColumns();
        creditOfferGrid.setWidth("34%");
        creditOfferGrid.addColumn(CreditOffer::getNameClient).setCaption("Surname");
        creditOfferGrid.setSelectionMode(Grid.SelectionMode.MULTI);
        creditOfferGrid.addSelectionListener(event -> {
            creditOffers = new ArrayList<>(event.getAllSelectedItems());
            viewPay.setEnabled(creditOffers.size() == 1);
            remove.setEnabled(creditOffers.size() == 1);
        });
        addComponent(creditOfferGrid);
    }
}
