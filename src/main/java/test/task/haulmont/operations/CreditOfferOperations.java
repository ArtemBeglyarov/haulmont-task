package test.task.haulmont.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.task.haulmont.entity.CreditOffer;
import test.task.haulmont.repository.CreditOfferRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CreditOfferOperations implements Operations<CreditOffer> {

    @Autowired
    CreditOfferRepository creditOfferRepository;

    @Override
    public void create(CreditOffer creditOffer) {
        creditOfferRepository.save(creditOffer);
    }

    @Override
    public CreditOffer find(UUID id) {
        return creditOfferRepository.getOne(id);
    }

    @Override
    public List<CreditOffer> getAll(){
        return creditOfferRepository.findAll();
    }

    @Override
    public  void deleteAll(List<CreditOffer> creditOffers){
        creditOfferRepository.deleteAll(creditOffers);
    }
    public void delete(UUID id) {

    }
}
