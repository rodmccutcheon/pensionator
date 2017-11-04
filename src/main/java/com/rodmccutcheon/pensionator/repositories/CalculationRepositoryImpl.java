package com.rodmccutcheon.pensionator.repositories;

import com.rodmccutcheon.pensionator.domain.Calculation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CalculationRepositoryImpl implements CalculationRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void detachCalculation(Calculation calculation) {
        entityManager.detach(calculation);
    }
}
