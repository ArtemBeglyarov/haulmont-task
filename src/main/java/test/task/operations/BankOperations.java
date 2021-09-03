package test.task.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.task.entity.Bank;
import test.task.repository.BankRepository;

import java.util.List;
import java.util.UUID;

@Service
public class BankOperations implements Operations<Bank> {

    @Autowired
    BankRepository bankRepository;

    @Override
    public void create(Bank bank) {
        bankRepository.save(bank);
    }

    @Override
    public Bank find(UUID id) {
        return bankRepository.getOne(id);
    }


    @Override
    public List<Bank> getAll() {
        return bankRepository.findAll();
    }

    public void delete(Bank bank) {
        bankRepository.delete(bank);
    }

    public void deleteAll(List<Bank> clients) {
        bankRepository.deleteAll(clients);
    }

}
