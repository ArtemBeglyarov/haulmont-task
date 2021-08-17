package test.task.haulmont.view;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import test.task.haulmont.entity.CreditOffer;
import test.task.haulmont.operations.ClientOperations;
import test.task.haulmont.operations.CreditOfferOperations;

import javax.annotation.PostConstruct;

@SpringView(name = "PaymentSchedule")
public class ViewPaymentScheduleRepository extends VerticalLayout implements View {

    Grid<CreditOffer> creditOfferGrid;

    @Autowired
    private ClientOperations clientOperations;

    @Autowired
    CreditOfferOperations creditOfferOperations;

    VerticalLayout vertical = new VerticalLayout();
    HorizontalLayout horizontalLayout = new HorizontalLayout();
    Button viewPay;
    @PostConstruct
    void init() {
        addComponent(horizontalLayout);
       horizontalLayout.addComponent(vertical);
        vertical.addComponent(viewPaymentSchedule());
    }

    private Grid<CreditOffer> viewPaymentSchedule() {
       creditOfferGrid= new Grid<>(CreditOffer.class);
        creditOfferGrid.setItems(creditOfferOperations.getAll());
        creditOfferGrid.removeAllColumns();
        creditOfferGrid.setWidth("50%");
        creditOfferGrid.addColumn(CreditOffer ::getPaymentSchedule).setCaption("pay");
        return creditOfferGrid;
    }
}
