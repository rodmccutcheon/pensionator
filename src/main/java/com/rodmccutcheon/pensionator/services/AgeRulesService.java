package com.rodmccutcheon.pensionator.services;

import com.rodmccutcheon.pensionator.domain.AgeRule;

import java.util.List;

public interface AgeRulesService  {

    List<AgeRule> listAllAgeRules();

    AgeRule getAgeRuleById(Long id);

    AgeRule saveAgeRule(AgeRule ageRule);

    void deleteAgeRule(Long id);
}
