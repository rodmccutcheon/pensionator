package com.rodmccutcheon.pensionator.services;

import com.rodmccutcheon.pensionator.domain.DeemingRateGroup;
import com.rodmccutcheon.pensionator.repositories.DeemingRateGroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DeemingRateGroupsServiceImpl implements DeemingRateGroupsService {

    private DeemingRateGroupsRepository deemingRateGroupsRepository;

    @Autowired
    public DeemingRateGroupsServiceImpl(DeemingRateGroupsRepository deemingRateGroupsRepository) {
        this.deemingRateGroupsRepository = deemingRateGroupsRepository;
    }

    @Override
    public List<DeemingRateGroup> listAllDeemingRateGroups() {
        return deemingRateGroupsRepository.findAll();
    }

    @Override
    public DeemingRateGroup getDeemingRateGroupById(Long id) {
        return deemingRateGroupsRepository.findOne(id);
    }

    @Override
    public DeemingRateGroup getDeemingRateGroupByDate(Date date, Date date2) {
        return deemingRateGroupsRepository.findDeemingRateGroupByStartDateLessThanEqualAndEndDateGreaterThanEqual(date, date2);
    }

    @Override
    public DeemingRateGroup saveDeemingRateGroup(DeemingRateGroup deemingRateGroup) {
        return deemingRateGroupsRepository.save(deemingRateGroup);
    }

    @Override
    public void deleteDeemingRateGroup(Long id) {
        deemingRateGroupsRepository.delete(id);
    }
}
