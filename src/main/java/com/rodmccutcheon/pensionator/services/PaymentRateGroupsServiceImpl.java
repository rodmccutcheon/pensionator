package com.rodmccutcheon.pensionator.services;

import com.rodmccutcheon.pensionator.domain.PaymentRateGroup;
import com.rodmccutcheon.pensionator.repositories.PaymentRateGroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaymentRateGroupsServiceImpl implements PaymentRateGroupsService {

    private PaymentRateGroupsRepository paymentRateGroupsRepository;

    @Autowired
    public PaymentRateGroupsServiceImpl(PaymentRateGroupsRepository paymentRateGroupsRepository) {
        this.paymentRateGroupsRepository = paymentRateGroupsRepository;
    }

    @Override
    public List<PaymentRateGroup> listAllPaymentRateGroups() {
        return paymentRateGroupsRepository.findAll();
    }

    @Override
    public PaymentRateGroup getPaymentRateGroupById(Long id) {
        return paymentRateGroupsRepository.findOne(id);
    }

    @Override
    public PaymentRateGroup getPaymentRateGroupByDate(Date date) {
        return paymentRateGroupsRepository.findPaymentRateGroupByStartDateLessThanEqualAndEndDateGreaterThanEqual(date, date);
    }

    @Override
    public PaymentRateGroup savePaymentRateGroup(PaymentRateGroup paymentRateGroup) {
        return paymentRateGroupsRepository.save(paymentRateGroup);
    }

    @Override
    public void deletePaymentRateGroup(Long id) {
        paymentRateGroupsRepository.delete(id);
    }

}
