package test.task.haulmont.view;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import test.task.haulmont.entity.Client;
import test.task.haulmont.operations.BankOperations;
import test.task.haulmont.operations.ClientOperations;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringView(name = "Banks")
public class ViewBank extends VerticalLayout implements View {

    @Autowired
    private ClientOperations clientOperations;
    @Autowired
    private BankOperations bankOperations;

    HorizontalLayout horizontalLayout = new HorizontalLayout();
    VerticalLayout vertical = new VerticalLayout();

    private Button add;
    private Button delete;
    private Button update;
    private Button find;
    private List<Client> clients;
    private Grid<Client> grid;


    @PostConstruct
    void init() {
        addComponent(horizontalLayout);

        horizontalLayout.addComponent(add = new Button("Create"));
        horizontalLayout.addComponent(update = new Button("Update"));
        horizontalLayout.addComponent(find = new Button("Find"));
        horizontalLayout.addComponent(delete = new Button("Delete"));
    }

}
