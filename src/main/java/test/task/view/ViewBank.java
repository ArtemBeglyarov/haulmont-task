package test.task.view;

import com.vaadin.annotations.Push;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import test.task.entity.Bank;
import test.task.entity.Client;
import test.task.operations.BankOperations;
import test.task.operations.ClientOperations;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
@Push
@SpringView(name = "Banks")
public class ViewBank extends VerticalLayout implements View {

    @Autowired
    private ClientOperations clientOperations;
    @Autowired
    private BankOperations bankOperations;

    HorizontalLayout horizontalLayout = new HorizontalLayout();
    HorizontalLayout horizontalLayout2 = new HorizontalLayout();
    VerticalLayout vertical = new VerticalLayout();

    private Button add;
    private Button delete;
    private Button update;
    private Button find;
    private Button view;
    private List<Bank> banks;
    private List<Client> clients;
    private Grid<Bank> grid;
    TextField name;

    @PostConstruct
    void init() {
        addComponent(horizontalLayout);
        addComponent(showAllBanks());

        horizontalLayout.addComponent(add = new Button("Add bank", (event -> getUI().addWindow(createUpdateBank()))));
        horizontalLayout.addComponent(update = new Button("Edit bank", event -> getUI().addWindow(createUpdateBank(banks.get(0)))));
        horizontalLayout.addComponent(find = new Button("Find info",event -> getUI().addWindow(findBank(banks.get(0)))));
        horizontalLayout.addComponent(delete = new Button("Remove bank", event -> {
            bankOperations.deleteAll(banks);
            getUI().getNavigator().navigateTo("Banks");
        }));
        add.setIcon(VaadinIcons.PIGGY_BANK);
        update.setIcon(VaadinIcons.PIGGY_BANK);
        delete.setIcon(VaadinIcons.PIGGY_BANK);

        find.setIcon(VaadinIcons.PIGGY_BANK);
        delete.setEnabled(false);
        find.setEnabled(false);
        update.setEnabled(false);
    }

    private Window createUpdateBank() {

        Bank bank = new Bank();
        Window window = new Window("Create Bank");
        VerticalLayout verticalWindow = new VerticalLayout();

        window.setContent(verticalWindow);
        verticalWindow.addComponent(name = new TextField("Name"));
        return getComponents(bank, window, verticalWindow);
    }

    private Window createUpdateBank(Bank bank) {

        Window window = new Window("Update Bank");
        VerticalLayout verticalWindow = new VerticalLayout();

        window.setContent(verticalWindow);
        verticalWindow.addComponent(name = new TextField("Name", bank.getName()));
        return getComponents(bank, window, verticalWindow);
    }

    private Window getComponents(Bank bank, Window window, VerticalLayout verticalWindow) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        horizontalLayout.addComponent(new Button("Ok", event -> {
            bank.setName(name.getValue());
            bankOperations.create(bank);
            window.close();
            getUI().getNavigator().navigateTo("Banks");
        }));

        horizontalLayout.addComponent(new Button("Close", event -> window.close()));
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
        vertical.addComponent(new Label("Credits" + ": " + bank.viewNameAllCredits()));
        vertical.addComponent(new Label("Clients" + ": " + bank.viewNameAllClients()));
        vertical.addComponent(new Button("close", event -> window.close()));
        window.center();
        window.setWidth("20%");
        window.setModal(true);
        return window;
    }

    private Grid<Bank> showAllBanks() {
        grid = new Grid<>(Bank.class);
        grid.setItems(bankOperations.getAll());
        grid.removeAllColumns();
        grid.setWidth("34%");
        grid.addColumn(Bank::getName).setCaption("Name");
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.addSelectionListener(event -> {
            banks = new ArrayList<>(event.getAllSelectedItems());
            delete.setEnabled(banks.size() > 0);
            find.setEnabled(banks.size() == 1);
            update.setEnabled(banks.size() == 1);
        });
        return grid;
    }

}
