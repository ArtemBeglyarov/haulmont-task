package test.task.haulmont.view;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;


@Theme("valo")
@SpringUI
@SpringViewDisplay
public class MainView extends UI implements ViewDisplay {

    VerticalLayout vertical = new VerticalLayout();
    private Panel panel;
    private final Button client = createNavigationButton("Clients","Clients");
    private final Button banks = createNavigationButton("Banks","Banks");
    private final Button credit = createNavigationButton("Credits","Credits");
    private final Button creditOffer = createNavigationButton("Credits offers","CreditOffers");
    private final Button paymentSchedule = createNavigationButton("Payment schedule","PaymentSchedule");

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setContent(vertical);
        vertical.addComponent(new Label("Select category"));


        HorizontalLayout buttons = new HorizontalLayout();
        buttons.addComponent(banks);
        buttons.addComponent(client);
        buttons.addComponent(credit);
        buttons.addComponent(creditOffer);
        buttons.addComponent(paymentSchedule);
        panel = new Panel();

        vertical.addComponent(buttons);
        vertical.addComponent(panel);
        vertical.setExpandRatio(panel, 1.0f);


    }

    private Button createNavigationButton(String caption, final String viewName) {
        Button button = new Button(caption);
        button.addStyleName(ValoTheme.BUTTON_QUIET);
        button.addClickListener(event -> getUI().getNavigator().navigateTo(viewName));
        return button;
    }
    @Override
    public void showView(View view) {
        panel.setContent((Component) view);
    }
}
