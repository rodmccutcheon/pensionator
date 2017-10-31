package com.rodmccutcheon.pensionator.controllers;

import com.rodmccutcheon.pensionator.services.DeemingRateGroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/deemingRates")
public class DeemingRatesController {

    private DeemingRateGroupsService deemingRateGroupsService;

    @Autowired
    public DeemingRatesController(DeemingRateGroupsService deemingRateGroupsService) {
        this.deemingRateGroupsService = deemingRateGroupsService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("deemingRateGroups", deemingRateGroupsService.listAllDeemingRateGroups());
        return "deemingRates/deemingRates";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("deemingRateGroup", deemingRateGroupsService.getDeemingRateGroupById(id));
        return "deemingRates/deemingRates-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        deemingRateGroupsService.deleteDeemingRateGroup(id);
        return "redirect:/deemingRates";
    }
}
