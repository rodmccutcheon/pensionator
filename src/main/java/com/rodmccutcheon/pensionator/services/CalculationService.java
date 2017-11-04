package com.rodmccutcheon.pensionator.services;

import com.rodmccutcheon.pensionator.domain.Calculation;

public interface CalculationService {

    Calculation getCalculationById(Long id);

    Calculation saveCalculation(Calculation calculation);

    Calculation cloneCalculation(Calculation calculation);

    void deleteCalculation(Long id);
}
