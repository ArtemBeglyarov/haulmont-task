package test.task.haulmont.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.task.haulmont.entity.Bank;
import test.task.haulmont.entity.Credit;
import test.task.haulmont.repository.BankRepository;

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
    public Bank update(UUID id) {
        return null;
    }


    public void delete(Bank bank) {
        bankRepository.delete(bank);
    }

    public List<Bank> getAll() {
        return bankRepository.findAll();
    }

    public void deleteAll(List<Bank> clients) {
        bankRepository.deleteAll(clients);
    }

    public void addCredit(UUID bankId, Credit credit) {
        Bank bank = find(bankId);
        bank.getCredits().add(credit);
        create(bank);
    }

    public void removeCredit(UUID bankId, Credit credit) {
        Bank bank = find(bankId);
        bank.getCredits().remove(credit);
        create(bank);
    }
}
