package test.task.haulmont.view;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import test.task.haulmont.operations.ClientOperations;

import javax.annotation.PostConstruct;

@SpringView(name = "Clients")
public class ViewClient extends VerticalLayout implements View {

    private final Button add = new Button("add client",(e-> getUI().addWindow(createClient())));
    private final Button delete = new Button("delete client",(e-> getUI().addWindow(deleteClient())));
    private final Button update = new Button("change client");
    private final Button find = new Button("find client");

    @Autowired
    private ClientOperations clientOperations;

    private final Button addButton = new Button("Добавить");
    VerticalLayout vertical = new VerticalLayout();

    @PostConstruct
    void init() {
        addComponent(vertical);
        vertical.addComponent(new Label("Select category CLients"));

//
        vertical.addComponent(add);
        vertical.addComponent(delete);
        vertical.addComponent(update);
        vertical.addComponent(find);

//        createClient();

    }

    private Window createClient() {
        Window window = new Window("add client");
        VerticalLayout verticalWindow = new VerticalLayout();
        window.setContent(verticalWindow);
        verticalWindow.addComponent(new TextField("Name"));
        verticalWindow.addComponent(new TextField("Surname"));
        verticalWindow.addComponent(new TextField("Patronymic"));
        verticalWindow.addComponent(new TextField("Email"));
        verticalWindow.addComponent(new TextField("Phone number"));
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(new Button("OK"));
        horizontalLayout.addComponent(new Button("CLOSE",event -> window.close()));
        verticalWindow.addComponent(horizontalLayout);
//        verticalWindow.addComponent(new Button("add"));
//        verticalWindow.addComponent(new Button("closed"));
        window.center();
        return window;
    }
    private Window deleteClient() {
        Window window = new Window("delete client");
        VerticalLayout verticalWindow = new VerticalLayout();
        window.setContent(verticalWindow);
        verticalWindow.addComponent(new TextField("input ID client"));
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.addComponent(new Button("OK"));
        horizontalLayout.addComponent(new Button("CLOSE",event -> window.close()));
        verticalWindow.addComponent(horizontalLayout);
        window.center();

        return window;
    }
    private Window updateClient() {
        Window window = new Window("edit client");
        return window;
    }
    private Window findClient() {
        Window window = new Window("");
        return  window;
    }

}
