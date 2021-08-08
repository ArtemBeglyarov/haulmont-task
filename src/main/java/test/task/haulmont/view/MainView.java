package test.task.haulmont.view;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;
import test.task.haulmont.entity.Client;


@Theme("valo")
@SpringUI
@SpringViewDisplay
public class MainView extends UI implements ViewDisplay {

    private Panel panel;
    private final Button client = new Button("Clients");

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        VerticalLayout content = new VerticalLayout();
//        content.setSizeFull();
        setContent(content);
        content.addComponent(new Label("TEST"));
        Grid<Client> grid = new Grid<>();

        panel = new Panel();
        content.addComponent(panel);
        content.addComponent(client);

        HorizontalLayout buttons = new HorizontalLayout();
//        buttons.addComponent(client);
    }

    @Override
    public void showView(View view) {
        panel.setContent((Component) view);
    }
}
