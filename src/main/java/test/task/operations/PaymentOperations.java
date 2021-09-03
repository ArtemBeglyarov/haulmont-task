package test.task.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.task.entity.PaymentSchedule;
import test.task.repository.PaymentRepository;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentOperations implements Operations<PaymentSchedule> {

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public void create(PaymentSchedule paymentSchedule) {
        paymentRepository.save(paymentSchedule);
    }

    @Override
    public PaymentSchedule find(UUID id) {
        return paymentRepository.getOne(id);
    }

    @Override
    public  void deleteAll(List<PaymentSchedule> paymentSchedule){
        paymentRepository.deleteAll(paymentSchedule);
    }

    @Override
    public List<PaymentSchedule>  getAll(){
        return paymentRepository.findAll();
    }

    public void delete(PaymentSchedule paymentSchedule) {
        paymentRepository.delete(paymentSchedule);
    }
}
