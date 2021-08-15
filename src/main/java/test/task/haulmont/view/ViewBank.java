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
    private List<Bank> banks;
    private List<Client> clients;
    private Grid<Bank> grid;
    TextField name;

    @PostConstruct
    void init() {
        addComponent(horizontalLayout);
        showAllBanks();
        horizontalLayout.addComponent(add = new Button("Create",(event -> getUI().addWindow(createUpdateBank()))));
        horizontalLayout.addComponent(update = new Button("Update", event -> getUI().addWindow(createUpdateBank(banks.get(0)))));
        horizontalLayout.addComponent(find = new Button("Find",event -> getUI().addWindow(findBank(banks.get(0)))));
        horizontalLayout.addComponent(delete = new Button("Delete",event -> {bankOperations.deleteAll(banks);getUI().getNavigator().navigateTo("Banks");}));
    }

    private Window createUpdateBank() {

        Bank bank = new Bank();
        Window window = new Window("Create Bank");
        VerticalLayout verticalWindow = new VerticalLayout();

        window.setContent(verticalWindow);
        verticalWindow.addComponent(name = new TextField("Name"));
        return getComponents(bank, window, verticalWindow);
    }
    private Window  createUpdateBank(Bank bank) {

        Window window = new Window("Update Bank");
        VerticalLayout verticalWindow = new VerticalLayout();

        window.setContent(verticalWindow);
        verticalWindow.addComponent(name = new TextField("Name",bank.getName()));
        return getComponents(bank, window, verticalWindow);
    }

    private Window getComponents(Bank bank, Window window, VerticalLayout verticalWindow) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        horizontalLayout.addComponent(new Button("OK", event -> {
            bank.setName(name.getValue());
            bankOperations.create(bank);
            window.close();
            getUI().getNavigator().navigateTo("Banks");}));

        horizontalLayout.addComponent(new Button("CLOSE", event -> window.close()));
        verticalWindow.addComponent(horizontalLayout);
        window.setModal(true);
        window.center();
        return window;
    }
    private Window findBank(Bank bank) {

        Window window = new Window("Bank information");
        VerticalLayout vertical = new VerticalLayout();

        bankOperations.find(bank.getID());
        window.setContent(vertical);
        vertical.addComponent(new Label("Name" + ": " + bank.getName()));
        vertical.addComponent(new Label("Credits" + ": " + bank.viewNameAllCredits(bank.getCredits())));
        vertical.addComponent(new Label("Clients" + ": " + bank.viewNameAllClients(bank.getClients())));
        vertical.addComponent(new Button("close", event -> window.close()));
        window.center();
        window.setWidth("20%");
        window.setModal(true);
        return window;
    }
    private void showAllBanks() {
        grid = new Grid<>(Bank.class);
        grid.setItems(bankOperations.getAll());
        grid.removeAllColumns();
        grid.setWidth("50%");
        grid.addColumn(Bank::getName).setCaption("Name");
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.addSelectionListener(event -> {
            banks = new ArrayList<>(event.getAllSelectedItems());
            delete.setEnabled(banks.size() > 0);
            find.setEnabled(banks.size() == 1);
            update.setEnabled(banks.size() == 1);
        });
        addComponents(grid);
    }
}
