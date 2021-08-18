package test.task.haulmont.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.task.haulmont.entity.Client;
import test.task.haulmont.repository.ClientRepository;
import java.util.List;
import java.util.UUID;

@Service
public class ClientOperations implements Operations<Client> {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public void create(Client client) {
        clientRepository.save(client);
    }

    @Override
    public Client find(UUID id) {
        return null;
    }

    @Override
    public List<Client> getAll(){
        return clientRepository.findAll();
    }

    @Override
    public void deleteAll(List<Client> clients) {
        clientRepository.deleteAll(clients);
    }
    public void delete(UUID id) {

    }
}

