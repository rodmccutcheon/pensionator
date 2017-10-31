package com.rodmccutcheon.pensionator.services;

import com.rodmccutcheon.pensionator.domain.HomeownerStatus;
import com.rodmccutcheon.pensionator.repositories.HomeownerStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeownerStatusesServiceImpl implements HomeownerStatusesService {

    private HomeownerStatusRepository homeownerStatusRepository;

    @Autowired
    public HomeownerStatusesServiceImpl(HomeownerStatusRepository homeownerStatusRepository) {
        this.homeownerStatusRepository = homeownerStatusRepository;
    }

    @Override
    public List<HomeownerStatus> listAllHomeownerStatuses() {
        return homeownerStatusRepository.findAll();
    }
}
