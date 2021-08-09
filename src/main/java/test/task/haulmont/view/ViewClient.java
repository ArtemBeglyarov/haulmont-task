package test.task.haulmont.view;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import test.task.haulmont.operations.ClientOperations;

import javax.annotation.PostConstruct;

@SpringView(name = "Clients")
public class ViewClient extends VerticalLayout implements View {


    @Autowired
    private   ClientOperations clientOperations;

    private final Button addButton = new Button("Добавить");
    VerticalLayout vertical = new VerticalLayout();

    @PostConstruct
    void init() {
        vertical.addComponent(new Label("Select category"));
//        configureButtons();
//        configureGrid();
    }

//    private void configureButtons() {
//        vertical.addComponent(addButton);
//    }
}
