package com.rodmccutcheon.pensionator.services;

import com.rodmccutcheon.pensionator.domain.PaymentRateGroup;

import java.util.Date;
import java.util.List;

public interface PaymentRateGroupsService {

    List<PaymentRateGroup> listAllPaymentRateGroups();

    PaymentRateGroup getPaymentRateGroupById(Long id);

    PaymentRateGroup getPaymentRateGroupByDate(Date date);

    PaymentRateGroup savePaymentRateGroup(PaymentRateGroup paymentRateGroup);

    void deletePaymentRateGroup(Long id);
}
