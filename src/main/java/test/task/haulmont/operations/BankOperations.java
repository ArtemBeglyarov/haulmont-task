package test.task.haulmont.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.task.haulmont.entity.Bank;
import test.task.haulmont.repository.BankRepository;

import java.util.UUID;

@Service
public class BankOperations implements Operations<Bank> {

    @Autowired
    BankRepository bankRepository;

    @Override
    public void create(Bank bank) {

    }

    @Override
    public Bank find(UUID id) {
        return null;
    }

    @Override
    public Bank update(UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
