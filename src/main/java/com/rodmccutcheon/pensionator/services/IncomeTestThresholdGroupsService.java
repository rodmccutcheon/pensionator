package com.rodmccutcheon.pensionator.services;

import com.rodmccutcheon.pensionator.domain.IncomeTestThresholdGroup;

import java.util.Date;
import java.util.List;

public interface IncomeTestThresholdGroupsService {

    List<IncomeTestThresholdGroup> listAllIncomeTestThresholdGroups();

    IncomeTestThresholdGroup getIncomeTestThresholdGroupById(Long id);

    IncomeTestThresholdGroup getIncomeTestThresholdGroupByDate(Date date);

    IncomeTestThresholdGroup saveIncomeTestThresholdGroup(IncomeTestThresholdGroup IncomeTestThresholdGroup);

    void deleteIncomeTestThresholdGroup(Long id);
}
