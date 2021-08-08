package test.task.haulmont.view;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import test.task.haulmont.operations.ClientOperations;

@SpringView(name = "Clients")
public class ViewClient extends VerticalLayout implements View {

    @Autowired
    private   ClientOperations clientOperations;

}
