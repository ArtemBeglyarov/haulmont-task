package test.task.haulmont.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.task.haulmont.entity.PaymentSchedule;

import java.util.UUID;

@Service
public class PaymentScheduleRepository implements Operations<PaymentSchedule>{

    @Autowired
    PaymentScheduleRepository paymentScheduleRepository;

    @Override
    public void create(PaymentSchedule paymentSchedule) {

    }

    @Override
    public PaymentSchedule find(UUID id) {
        return null;
    }

    @Override
    public PaymentSchedule update(UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
