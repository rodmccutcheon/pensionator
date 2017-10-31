package com.rodmccutcheon.pensionator.controllers;

import com.rodmccutcheon.pensionator.domain.PaymentRate;
import com.rodmccutcheon.pensionator.domain.PaymentRateGroup;
import com.rodmccutcheon.pensionator.domain.RelationshipStatus;
import com.rodmccutcheon.pensionator.services.PaymentRateGroupsService;
import com.rodmccutcheon.pensionator.services.RelationshipStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/paymentRates")
public class PaymentRatesController {

    private PaymentRateGroupsService paymentRateGroupsService;
    private RelationshipStatusService relationshipStatusService;

    @Autowired
    public PaymentRatesController(PaymentRateGroupsService paymentRateGroupsService, RelationshipStatusService relationshipStatusService) {
        this.paymentRateGroupsService = paymentRateGroupsService;
        this.relationshipStatusService = relationshipStatusService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("paymentRateGroups", paymentRateGroupsService.listAllPaymentRateGroups());
        return "paymentRates/paymentRates";
    }

    @GetMapping("/new")
    public String create(Model model) {
        PaymentRateGroup paymentRateGroup = new PaymentRateGroup();
        List<PaymentRate> paymentRates = new ArrayList<>();
        for (RelationshipStatus relationshipStatus : relationshipStatusService.listAllRelationshipStatuses()) {
            paymentRates.add(new PaymentRate(relationshipStatus));
        }
        paymentRateGroup.setPaymentRates(paymentRates);
        model.addAttribute("paymentRateGroup", paymentRateGroup);
        return "paymentRates/paymentRates-form";
    }

    @PostMapping
    public String save(@Valid PaymentRateGroup paymentRateGroup, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "paymentRates/paymentRates-form";
        }
        paymentRateGroupsService.savePaymentRateGroup(paymentRateGroup);
        return "redirect:/paymentRates";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("paymentRateGroup", paymentRateGroupsService.getPaymentRateGroupById(id));
        return "paymentRates/paymentRates-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        paymentRateGroupsService.deletePaymentRateGroup(id);
        return "redirect:/paymentRates";
    }

}
