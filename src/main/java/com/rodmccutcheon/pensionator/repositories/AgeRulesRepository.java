package com.rodmccutcheon.pensionator.repositories;

import com.rodmccutcheon.pensionator.domain.AgeRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgeRulesRepository extends JpaRepository<AgeRule, Long> {
}
