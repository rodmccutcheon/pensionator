package com.rodmccutcheon.pensionator.repositories;

import com.rodmccutcheon.pensionator.domain.RelationshipStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RelationshipStatusRepository extends JpaRepository<RelationshipStatus, Long> {

    RelationshipStatus findByName(String name);
}
