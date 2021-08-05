package test.task.haulmont.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.task.haulmont.entity.Credit;
import test.task.haulmont.repository.CreditRepository;

import java.util.UUID;

@Service
public class CreditOperations implements Operations<Credit>{

    @Autowired
    CreditRepository creditRepository;

    @Override
    public void create(Credit credit) {

    }

    @Override
    public Credit find(UUID id) {
        return null;
    }

    @Override
    public Credit update(UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
