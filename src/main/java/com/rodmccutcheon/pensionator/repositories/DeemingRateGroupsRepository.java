package com.rodmccutcheon.pensionator.repositories;

import com.rodmccutcheon.pensionator.domain.DeemingRateGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface DeemingRateGroupsRepository extends JpaRepository<DeemingRateGroup, Long> {

    DeemingRateGroup findDeemingRateGroupByStartDateLessThanEqualAndEndDateGreaterThanEqual(Date date, Date date2);
}
