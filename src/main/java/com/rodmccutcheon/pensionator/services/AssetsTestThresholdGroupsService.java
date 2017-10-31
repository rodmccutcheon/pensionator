package com.rodmccutcheon.pensionator.services;

import com.rodmccutcheon.pensionator.domain.AssetsTestThresholdGroup;

import java.util.Date;
import java.util.List;

public interface AssetsTestThresholdGroupsService {

    List<AssetsTestThresholdGroup> listAllAssetsTestThresholdGroups();

    AssetsTestThresholdGroup getAssetsTestThresholdGroupById(Long id);

    AssetsTestThresholdGroup getAssetsTestThresholdGroupByDate(Date date, Date date2);

    AssetsTestThresholdGroup saveAssetsTestThresholdGroup(AssetsTestThresholdGroup assetsTestThresholdGroup);

    void deleteAssetsTestThresholdGroup(Long id);
}
