package com.rodmccutcheon.pensionator.services;

import com.rodmccutcheon.pensionator.domain.IncomeTestThresholdGroup;
import com.rodmccutcheon.pensionator.repositories.IncomeTestThresholdGroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IncomeTestThresholdGroupsServiceImpl implements IncomeTestThresholdGroupsService {

    private IncomeTestThresholdGroupsRepository incomeTestThresholdGroupsRepository;

    @Autowired
    public IncomeTestThresholdGroupsServiceImpl(IncomeTestThresholdGroupsRepository incomeTestThresholdGroupsRepository) {
        this.incomeTestThresholdGroupsRepository = incomeTestThresholdGroupsRepository;
    }

    @Override
    public List<IncomeTestThresholdGroup> listAllIncomeTestThresholdGroups() {
        return incomeTestThresholdGroupsRepository.findAll();
    }

    @Override
    public IncomeTestThresholdGroup getIncomeTestThresholdGroupById(Long id) {
        return incomeTestThresholdGroupsRepository.findOne(id);
    }

    @Override
    public IncomeTestThresholdGroup getIncomeTestThresholdGroupByDate(Date date) {
        return incomeTestThresholdGroupsRepository.findIncomeTestThresholdGroupByStartDateLessThanEqualAndEndDateGreaterThanEqual(date, date);
    }

    @Override
    public IncomeTestThresholdGroup saveIncomeTestThresholdGroup(IncomeTestThresholdGroup incomeTestThresholdGroup) {
        return incomeTestThresholdGroupsRepository.save(incomeTestThresholdGroup);
    }

    @Override
    public void deleteIncomeTestThresholdGroup(Long id) {
        incomeTestThresholdGroupsRepository.delete(id);
    }
}
