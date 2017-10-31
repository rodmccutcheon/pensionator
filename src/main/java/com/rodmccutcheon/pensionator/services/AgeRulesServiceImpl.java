package com.rodmccutcheon.pensionator.services;

import com.rodmccutcheon.pensionator.domain.AgeRule;
import com.rodmccutcheon.pensionator.repositories.AgeRulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgeRulesServiceImpl implements AgeRulesService {

    private AgeRulesRepository ageRulesRepository;

    @Autowired
    public AgeRulesServiceImpl(AgeRulesRepository ageRulesRepository) {
        this.ageRulesRepository = ageRulesRepository;
    }

    @Override
    public List<AgeRule> listAllAgeRules() {
        return ageRulesRepository.findAll();
    }

    @Override
    public AgeRule getAgeRuleById(Long id) {
        return ageRulesRepository.findOne(id);
    }

    @Override
    public AgeRule saveAgeRule(AgeRule ageRule) {
        return ageRulesRepository.save(ageRule);
    }

    @Override
    public void deleteAgeRule(Long id) {
        ageRulesRepository.delete(id);
    }
}
