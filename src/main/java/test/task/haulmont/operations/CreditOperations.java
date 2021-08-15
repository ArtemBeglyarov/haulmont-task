package test.task.haulmont.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.task.haulmont.entity.Credit;
import test.task.haulmont.repository.CreditRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CreditOperations implements Operations<Credit> {

    @Autowired
    CreditRepository creditRepository;

    @Override
    public void create(Credit credit) {
        creditRepository.save(credit);
    }

    @Override
    public Credit find(UUID id) {
        return creditRepository.getOne(id);
    }

    @Override
    public Credit update(UUID id) {
        return null;
    }


    public void delete(Credit credit) {
        creditRepository.delete(credit);
    }

    public List<Credit> getAll() {
        return creditRepository.findAll();
    }

    public void deleteAll(List<Credit> credits) {
        creditRepository.deleteAll(credits);
    }
}
