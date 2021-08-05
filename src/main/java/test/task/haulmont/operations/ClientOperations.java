package test.task.haulmont.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.task.haulmont.entity.Client;
import test.task.haulmont.repository.ClientRepository;

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
    public Client update(UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}

