package test.task.haulmont.view;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

@SpringView(name = "Banks")
public class ViewBank extends VerticalLayout implements View {

    VerticalLayout vertical = new VerticalLayout();


    @PostConstruct
    void init() {
        addComponent(vertical);
        vertical.addComponent(new Label("Select category"));
    }


}
