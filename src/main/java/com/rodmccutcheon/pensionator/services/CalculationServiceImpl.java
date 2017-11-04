package com.rodmccutcheon.pensionator.services;

import com.rodmccutcheon.pensionator.domain.Calculation;
import com.rodmccutcheon.pensionator.repositories.CalculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculationServiceImpl implements CalculationService {

    private CalculationRepository calculationRepository;

    @Autowired
    public CalculationServiceImpl(CalculationRepository calculationRepository) {
        this.calculationRepository = calculationRepository;
    }

    @Override
    public Calculation getCalculationById(Long id) {
        return calculationRepository.findOne(id);
    }

    @Override
    public Calculation saveCalculation(Calculation calculation) {
        return calculationRepository.save(calculation);
    }

    @Override
    public Calculation cloneCalculation(Calculation calculation) {
        calculationRepository.detachCalculation(calculation);
        calculation.setId(0);
        calculation.getAssets().stream().forEach(a -> a.setId(0));
        calculation.getIncomeStreams().stream().forEach(i -> i.setId(0));
        return calculationRepository.save(calculation);
    }

    @Override
    public void deleteCalculation(Long id) {
        calculationRepository.delete(id);
    }
}
