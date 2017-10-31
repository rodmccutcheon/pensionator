package com.rodmccutcheon.pensionator.services;

import com.rodmccutcheon.pensionator.domain.RelationshipStatus;
import com.rodmccutcheon.pensionator.repositories.RelationshipStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RelationshipStatusServiceImpl implements RelationshipStatusService {

    private RelationshipStatusRepository relationshipStatusRepository;

    @Autowired
    public RelationshipStatusServiceImpl(RelationshipStatusRepository relationshipStatusRepository) {
        this.relationshipStatusRepository = relationshipStatusRepository;
    }

    @Override
    public List<RelationshipStatus> listAllRelationshipStatuses() {
        return relationshipStatusRepository.findAll();
    }

    @Override
    public RelationshipStatus findByName(String name) {
        return relationshipStatusRepository.findByName(name);
    }
}
