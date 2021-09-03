package test.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.task.entity.PaymentSchedule;

import java.util.UUID;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentSchedule, UUID> {
}
