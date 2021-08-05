package test.task.haulmont.view;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import test.task.haulmont.entity.Client;

import javax.swing.text.html.parser.ContentModel;


public class MainView extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        VerticalLayout content = new VerticalLayout();
        content.setSizeFull();
        setContent(content);

        content.addComponent(new Label("TEST"));

        Grid<Client> grid = new Grid<>();
        grid.setCaption("My Grid");
        grid.setItems(GridExample.generateContent());
        grid.setSizeFull();
        content.addComponent(grid);
        content.setExpandRatio(grid, 1);
    }
}
