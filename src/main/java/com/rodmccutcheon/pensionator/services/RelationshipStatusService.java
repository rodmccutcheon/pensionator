package com.rodmccutcheon.pensionator.services;

import com.rodmccutcheon.pensionator.domain.RelationshipStatus;

import java.util.List;

public interface RelationshipStatusService {

    List<RelationshipStatus> listAllRelationshipStatuses();

    RelationshipStatus findByName(String name);
}
