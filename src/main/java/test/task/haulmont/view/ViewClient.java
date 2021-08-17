package test.task.haulmont.view;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import test.task.haulmont.entity.Bank;
import test.task.haulmont.entity.Client;
import test.task.haulmont.operations.BankOperations;
import test.task.haulmont.operations.ClientOperations;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SpringView(name = "Clients")
public class ViewClient extends VerticalLayout implements View {

    private Button add;
    private Button delete;
    private Button update;
    private Button find;
    private List<Client> clients;
    private Grid<Client> grid;
    NativeSelect<Bank> bankNativeSelect;
    Set<Bank> banks;

    TextField name;
    TextField surname;
    TextField patronymic;
    TextField phone;
    TextField email;
    TextField passportNumber;

    @Autowired
    private ClientOperations clientOperations;

    @Autowired
    private BankOperations bankOperations;

    HorizontalLayout horizontalLayout = new HorizontalLayout();

    @PostConstruct
    void init() {
        addComponent(horizontalLayout);
        showAllClients();
        horizontalLayout.addComponent(add = new Button("Create client", (event -> getUI().addWindow(createUpdateClient()))));
        horizontalLayout.addComponent(update = new Button("Edit client", event -> getUI().addWindow(createUpdateClient(clients.get(0)))));
        horizontalLayout.addComponent(find = new Button("View client", event -> getUI().addWindow(findClient(clients.get(0)))));
        horizontalLayout.addComponent(delete = new Button("Delete client", event -> {clientOperations.deleteAll(clients);getUI().getNavigator().navigateTo("Clients");}));
        delete.setEnabled(false);
        find.setEnabled(false);
        update.setEnabled(false);
    }


    private Window createUpdateClient() {

        Client client = new Client();

        Window window = new Window("add client");
        VerticalLayout verticalWindow = new VerticalLayout();
        bankNativeSelect = new NativeSelect<Bank>("Select Bank",bankOperations.getAll());
        bankNativeSelect.setItemCaptionGenerator(Bank ::getName);
        window.setContent(verticalWindow);
        verticalWindow.addComponent(name = new TextField("Name"));
        verticalWindow.addComponent(surname = new TextField("Surname"));
        verticalWindow.addComponent(patronymic = new TextField("Patronymic"));
        verticalWindow.addComponent(email = new TextField("Email"));
        verticalWindow.addComponent(phone = new TextField("Phone number"));
        verticalWindow.addComponent(passportNumber = new TextField("Passport number"));
        verticalWindow.addComponent(bankNativeSelect);

        return getComponents(client, window, verticalWindow, name, surname, patronymic, email, phone, passportNumber, banks,bankNativeSelect);
    }

    private Window createUpdateClient(Client client) {
        bankNativeSelect = new NativeSelect<Bank>("Select Bank",bankOperations.getAll());
        bankNativeSelect.setItemCaptionGenerator(Bank ::getName);
        Window window = new Window("Edit client");
        VerticalLayout verticalWindow = new VerticalLayout();

        window.setContent(verticalWindow);
        verticalWindow.addComponent(name = new TextField("Name", client.getName()));
        verticalWindow.addComponent(surname = new TextField("Surname", client.getSurname()));
        verticalWindow.addComponent(patronymic = new TextField("Patronymic", client.getPatronymic()));
        verticalWindow.addComponent(email = new TextField("Email", client.getEmail()));
        verticalWindow.addComponent(phone = new TextField("Phone number", String.valueOf(client.getNumberPhone())));
        verticalWindow.addComponent(passportNumber = new TextField("Passport number", client.getPassportID()));
        verticalWindow.addComponent(bankNativeSelect);
        return getComponents(client, window, verticalWindow, name, surname, patronymic, email, phone, passportNumber,banks, bankNativeSelect);

    }

    private Window getComponents(Client client, Window window, VerticalLayout verticalWindow, TextField name, TextField surname, TextField patronymic, TextField email, TextField phone, TextField passportNumber, Set<Bank> banks, NativeSelect<Bank> bankNativeSelect) {



        HorizontalLayout horizontalLayout = new HorizontalLayout();

        horizontalLayout.addComponent(new Button("OK", event -> {

            client.setName(name.getValue());
            client.setSurname(surname.getValue());
            client.setPatronymic(patronymic.getValue());
            client.setEmail(email.getValue());
            client.setNumberPhone(Long.parseLong(phone.getValue()));
            client.setPassportID(passportNumber.getValue());
            client.getBanks().add(bankNativeSelect.getValue());
            clientOperations.create(client);
            window.close();
            getUI().getNavigator().navigateTo("Clients");}));

        horizontalLayout.addComponent(new Button("CLOSE", event -> window.close()));
        verticalWindow.addComponent(horizontalLayout);
        window.setModal(true);
        window.center();
        return window;
    }
//    private Window deleteClient() {
//        Window window = new Window("delete client");
//        VerticalLayout verticalWindow = new VerticalLayout();
//        window.setContent(verticalWindow);
//        verticalWindow.addComponent(new TextField("input ID client"));
//        HorizontalLayout horizontalLayout = new HorizontalLayout();
//        horizontalLayout.addComponent(new Button("OK"));
//        horizontalLayout.addComponent(new Button("CLOSE", event -> window.close()));
//        verticalWindow.addComponent(horizontalLayout);
//        window.center();
//
//        return window;

//    }

    private Window findClient(Client client) {

        Window window = new Window("Client information");
        VerticalLayout vertical = new VerticalLayout();

        clientOperations.find(client.getId());
        window.setContent(vertical);
        vertical.addComponent(new Label("Name" + ": " + client.getName()));
        vertical.addComponent(new Label("Surname" + ": " + client.getSurname()));
        vertical.addComponent(new Label("Patronymic" + ": " + client.getPatronymic()));
        vertical.addComponent(new Label("Phone number" + ": " + client.getNumberPhone()));
        vertical.addComponent(new Label("Email" + ": " + client.getEmail()));
        vertical.addComponent(new Label("Passport ID" + ": " + client.getPassportID()));
        vertical.addComponent(new Label("Banks" + ": " + client.viewNameAllBank(client.getBanks())));
        vertical.addComponent(new Button("close", event -> window.close()));
        window.center();
        window.setWidth("20%");
        window.setModal(true);
        return window;
    }

    private void showAllClients() {
        grid = new Grid<>(Client.class);
        grid.setItems(clientOperations.getAll());
        grid.removeAllColumns();
        grid.setWidth("50%");
        grid.addColumn(Client::getName).setCaption("Name");
        grid.addColumn(Client::getSurname).setCaption("Surname");
        grid.addColumn(Client::getPatronymic).setCaption("Patronymic");
//        grid.addColumn(Client::getNumberPhone).setCaption("Phone");
//        grid.addColumn(Client::getEmail).setCaption("Email");
//        grid.addColumn(Client::getPassportID).setCaption("Passport ID");
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.addSelectionListener(event -> {
            clients = new ArrayList<>(event.getAllSelectedItems());
            delete.setEnabled(clients.size() > 0);
            find.setEnabled(clients.size() == 1);
            update.setEnabled(clients.size() == 1);
            });
        addComponents(grid);
    }
}
