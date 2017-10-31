package com.rodmccutcheon.pensionator.repositories;

import com.rodmccutcheon.pensionator.domain.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalculationRepository extends JpaRepository<Calculation, Long> {
}
