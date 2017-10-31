package com.rodmccutcheon.pensionator.repositories;

import com.rodmccutcheon.pensionator.domain.HomeownerStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeownerStatusRepository extends JpaRepository<HomeownerStatus, Long> {
}
