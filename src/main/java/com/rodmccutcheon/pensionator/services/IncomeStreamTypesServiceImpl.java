package com.rodmccutcheon.pensionator.services;

import com.rodmccutcheon.pensionator.domain.IncomeStreamType;
import com.rodmccutcheon.pensionator.repositories.IncomeStreamTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeStreamTypesServiceImpl implements IncomeStreamTypesService {

    private IncomeStreamTypesRepository incomeStreamTypesRepository;

    @Autowired
    public IncomeStreamTypesServiceImpl(IncomeStreamTypesRepository incomeStreamTypesRepository) {
        this.incomeStreamTypesRepository = incomeStreamTypesRepository;
    }

    @Override
    public List<IncomeStreamType> listAllIncomeStreamTypes() {
        return incomeStreamTypesRepository.findAll();
    }
}
