package test.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.task.entity.Bank;
import java.util.UUID;

@Repository
public interface BankRepository extends JpaRepository<Bank, UUID> {
}
