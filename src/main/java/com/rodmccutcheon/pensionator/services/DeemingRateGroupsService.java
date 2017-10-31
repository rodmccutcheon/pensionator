package com.rodmccutcheon.pensionator.services;

import com.rodmccutcheon.pensionator.domain.DeemingRateGroup;

import java.util.Date;
import java.util.List;

public interface DeemingRateGroupsService {

    List<DeemingRateGroup> listAllDeemingRateGroups();

    DeemingRateGroup getDeemingRateGroupById(Long id);

    DeemingRateGroup getDeemingRateGroupByDate(Date date, Date date2);

    DeemingRateGroup saveDeemingRateGroup(DeemingRateGroup deemingRateGroup);

    void deleteDeemingRateGroup(Long id);
}
