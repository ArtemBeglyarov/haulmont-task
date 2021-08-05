package test.task.haulmont.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.task.haulmont.entity.CreditOffer;
import test.task.haulmont.repository.CreditOfferRepository;

import java.util.UUID;

@Service
public class CreditOfferOperations implements Operations<CreditOffer> {

    @Autowired
    CreditOfferRepository creditOfferRepository;

    @Override
    public void create(CreditOffer creditOffer) {

    }

    @Override
    public CreditOffer find(UUID id) {
        return null;
    }

    @Override
    public CreditOffer update(UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
