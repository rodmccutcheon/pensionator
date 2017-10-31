package com.rodmccutcheon.pensionator.services;

import com.rodmccutcheon.pensionator.domain.AssetsTestThresholdGroup;
import com.rodmccutcheon.pensionator.repositories.AssetsTestThresholdGroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AssetsTestThresholdGroupsServiceImpl implements AssetsTestThresholdGroupsService {

    private AssetsTestThresholdGroupsRepository assetsTestThresholdGroupsRepository;

    @Autowired
    public AssetsTestThresholdGroupsServiceImpl(AssetsTestThresholdGroupsRepository assetsTestThresholdGroupsRepository) {
        this.assetsTestThresholdGroupsRepository = assetsTestThresholdGroupsRepository;
    }

    @Override
    public List<AssetsTestThresholdGroup> listAllAssetsTestThresholdGroups() {
        return assetsTestThresholdGroupsRepository.findAll();
    }

    @Override
    public AssetsTestThresholdGroup getAssetsTestThresholdGroupById(Long id) {
        return assetsTestThresholdGroupsRepository.findOne(id);
    }

    @Override
    public AssetsTestThresholdGroup getAssetsTestThresholdGroupByDate(Date date) {
        return assetsTestThresholdGroupsRepository.findAssetsTestThresholdGroupByStartDateLessThanEqualAndEndDateGreaterThanEqual(date, date);
    }

    @Override
    public AssetsTestThresholdGroup saveAssetsTestThresholdGroup(AssetsTestThresholdGroup assetsTestThresholdGroup) {
        return assetsTestThresholdGroupsRepository.save(assetsTestThresholdGroup);
    }

    @Override
    public void deleteAssetsTestThresholdGroup(Long id) {
        assetsTestThresholdGroupsRepository.delete(id);
    }
}
