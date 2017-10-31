package com.rodmccutcheon.pensionator.repositories;

import com.rodmccutcheon.pensionator.domain.IncomeStreamType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncomeStreamTypesRepository extends JpaRepository<IncomeStreamType, Long> {
}
