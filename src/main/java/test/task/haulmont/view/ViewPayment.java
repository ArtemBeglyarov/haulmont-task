package test.task.haulmont.view;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import test.task.haulmont.entity.Client;
import test.task.haulmont.entity.CreditOffer;
import test.task.haulmont.entity.PaymentSchedule;
import test.task.haulmont.operations.ClientOperations;
import test.task.haulmont.operations.CreditOfferOperations;

import javax.annotation.PostConstruct;

@SpringView(name = "PaymentSchedule")
public class ViewPayment extends VerticalLayout implements View {

    Grid<PaymentSchedule> paymentScheduleGrid;

    @Autowired
    private ClientOperations clientOperations;

    @Autowired
    CreditOfferOperations creditOfferOperations;

    Grid<Client> grid;
    Grid<CreditOffer> creditOfferGrid;

    VerticalLayout vertical = new VerticalLayout();
    HorizontalLayout horizontalLayout = new HorizontalLayout();
    Button viewPay;

    @PostConstruct
    void init() {

        addComponent(horizontalLayout);
        addComponent(showAllClient());
        vertical.addComponent(viewPaymentSchedule());
    }

    private Grid<PaymentSchedule> viewPaymentSchedule() {
        paymentScheduleGrid = new Grid<>(PaymentSchedule.class);
        paymentScheduleGrid.setItems(creditOfferOperations.getAll().get(0).getPaymentSchedule());
        paymentScheduleGrid.removeAllColumns();
        paymentScheduleGrid.setWidth("100%");
        paymentScheduleGrid.addColumn(PaymentSchedule::getPayDay).setCaption("Day payment");
        paymentScheduleGrid.addColumn(PaymentSchedule::getPaymentSum).setCaption("Payment sum");
        paymentScheduleGrid.addColumn(PaymentSchedule::getPaymentBodyCredit).setCaption("Payment body credit");
        paymentScheduleGrid.addColumn(PaymentSchedule::getPaymentPercent).setCaption("Pay percent");
        return paymentScheduleGrid;
    }

    private Grid<CreditOffer> showAllClient() {
        creditOfferGrid = new Grid<>(CreditOffer.class);
        creditOfferGrid.setItems(creditOfferOperations.getAll());
        creditOfferGrid.removeAllColumns();
        creditOfferGrid.setWidth("34%");
        creditOfferGrid.addColumn(CreditOffer::getClient).setCaption("Surname");
        creditOfferGrid.setSelectionMode(Grid.SelectionMode.MULTI);
//        creditOfferGrid.addSelectionListener(event -> {
//            banks = new ArrayList<>(event.getAllSelectedItems());
//            delete.setEnabled(banks.size() > 0);
//            find.setEnabled(banks.size() == 1);
//            update.setEnabled(banks.size() == 1);
////            view.setEnabled(banks.size() == 1);
//        });
        return creditOfferGrid;
    }
}
