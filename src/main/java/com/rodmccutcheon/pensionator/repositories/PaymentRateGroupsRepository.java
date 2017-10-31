package com.rodmccutcheon.pensionator.repositories;

import com.rodmccutcheon.pensionator.domain.PaymentRateGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface PaymentRateGroupsRepository extends JpaRepository<PaymentRateGroup, Long> {

    PaymentRateGroup findPaymentRateGroupByStartDateLessThanEqualAndEndDateGreaterThanEqual(Date date, Date date2);
}
