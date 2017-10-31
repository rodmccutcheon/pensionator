package com.rodmccutcheon.pensionator.repositories;

import com.rodmccutcheon.pensionator.domain.AssetsTestThresholdGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface AssetsTestThresholdGroupsRepository extends JpaRepository<AssetsTestThresholdGroup, Long> {

    AssetsTestThresholdGroup findAssetsTestThresholdGroupByStartDateLessThanEqualAndEndDateGreaterThanEqual(Date date, Date date2);
}
