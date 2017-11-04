package com.rodmccutcheon.pensionator.repositories;

import com.rodmccutcheon.pensionator.domain.Calculation;

public interface CalculationRepositoryCustom {

    void detachCalculation(Calculation calculation);
}
