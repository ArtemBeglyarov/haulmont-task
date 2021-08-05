package test.task.haulmont.view;

import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import test.task.haulmont.operations.ClientOperations;

public class ViewClient extends VerticalLayout {

    @Autowired
    private   ClientOperations clientOperations;

}
