package com.rodmccutcheon.pensionator.repositories;

import com.rodmccutcheon.pensionator.domain.IncomeTestThresholdGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface IncomeTestThresholdGroupsRepository extends JpaRepository<IncomeTestThresholdGroup, Long> {

    IncomeTestThresholdGroup findIncomeTestThresholdGroupByStartDateBeforeAndEndDateAfter(Date date, Date date2);
}
