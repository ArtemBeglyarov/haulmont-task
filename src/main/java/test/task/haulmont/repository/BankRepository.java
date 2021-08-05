package test.task.haulmont.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.task.haulmont.entity.Bank;
import java.util.UUID;

@Repository
public interface BankRepository extends JpaRepository<Bank, UUID> {
}
