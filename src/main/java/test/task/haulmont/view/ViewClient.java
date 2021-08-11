package test.task.haulmont.view;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import test.task.haulmont.entity.Client;
import test.task.haulmont.operations.ClientOperations;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringView(name = "Clients")
public class ViewClient extends VerticalLayout implements View {

    private Button add = new Button("add client", (e -> getUI().addWindow(createClient())));
    private Button delete;
    private Button update = new Button("change client");
    private Button find = new Button("find client");
    private List<Client> clients;
    private Grid<Client> grid;

    @Autowired
    private ClientOperations clientOperations;
    VerticalLayout vertical = new VerticalLayout();
    HorizontalLayout horizontalLayout = new HorizontalLayout();

    @PostConstruct
    void init() {
        addComponent(horizontalLayout);
        horizontalLayout.addComponent(add);
        horizontalLayout.addComponent(update);
        horizontalLayout.addComponent(find);
        horizontalLayout.addComponent(vertical);
        showAllClients();
        delete = new Button("delete",event -> clientOperations.deleteAll(clients));
        horizontalLayout.addComponent(delete);
    }

    private Window createClient() {


        Client client = new Client();
        TextField name;
        TextField surname;
        TextField patronymic;
        TextField phone;
        TextField email;
        TextField passportNumber;

        Window window = new Window("add client");
        VerticalLayout verticalWindow = new VerticalLayout();
        window.setContent(verticalWindow);
        verticalWindow.addComponent(name = new TextField("Name"));
        verticalWindow.addComponent(surname = new TextField("Surname"));
        verticalWindow.addComponent(patronymic = new TextField("Patronymic"));
        verticalWindow.addComponent(email = new TextField("Email"));
        verticalWindow.addComponent(phone = new TextField("Phone number"));
        verticalWindow.addComponent(passportNumber = new TextField("Passport number"));
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        client.setName(name.getValue());
        client.setSurname(surname.getValue());
        client.setPatronymic(patronymic.getValue());
        client.setEmail(email.getValue());
//       );


        horizontalLayout.addComponent(new Button("OK", event -> {
            client.setName(name.getValue());
            client.setSurname(surname.getValue());
            client.setPatronymic(patronymic.getValue());
            client.setEmail(email.getValue());
            client.setNumberPhone(Long.parseLong(phone.getValue()));
            client.setPassportID(passportNumber.getValue());
            System.out.println(client.toString());
            clientOperations.create(client);
            window.close();
        }));
        horizontalLayout.addComponent(new Button("CLOSE", event -> window.close()));
        verticalWindow.addComponent(horizontalLayout);

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
        horizontalLayout.addComponent(new Button("CLOSE", event -> window.close()));
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
        return window;
    }
    private void showAllClients() {
        grid = new Grid<>(Client.class);
        grid.setItems(clientOperations.getAll());
        grid.removeAllColumns();
        grid.setWidth("100%");
        grid.addColumn(Client::getName).setCaption("Name");
        grid.addColumn(Client::getSurname).setCaption("Surname");
        grid.addColumn(Client::getPatronymic).setCaption("Patronymic");
        grid.addColumn(Client::getNumberPhone).setCaption("Phone");
        grid.addColumn(Client::getEmail).setCaption("Email");
        grid.addColumn(Client::getPassportID).setCaption("Passport ID");

        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.addSelectionListener(event -> {
        clients = new ArrayList<>(event.getAllSelectedItems());
        delete.setEnabled(clients.size() > 0);
//        editClientButton.setEnabled(selected.size() == 1);
//        showListOfBanks.setEnabled(selected.size() == 1);
        });
        addComponents(grid);
    }
}
