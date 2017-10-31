package com.rodmccutcheon.pensionator.controllers;

import com.rodmccutcheon.pensionator.domain.*;
import com.rodmccutcheon.pensionator.services.IncomeTestThresholdGroupsService;
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
@RequestMapping("/incomeTestThresholds")
public class IncomeTestThresholdsController {

    private IncomeTestThresholdGroupsService incomeTestThresholdGroupsService;
    private RelationshipStatusService relationshipStatusService;
    
    @Autowired
    public IncomeTestThresholdsController(IncomeTestThresholdGroupsService incomeTestThresholdGroupsService,
                                          RelationshipStatusService relationshipStatusService) {
        this.incomeTestThresholdGroupsService = incomeTestThresholdGroupsService;
        this.relationshipStatusService = relationshipStatusService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("incomeTestThresholdGroups", incomeTestThresholdGroupsService.listAllIncomeTestThresholdGroups());
        return "incomeTestThresholds/incomeTestThresholds";
    }

    @GetMapping("/new")
    public String create(Model model) {
        IncomeTestThresholdGroup incomeTestThresholdGroup = new IncomeTestThresholdGroup();
        List<IncomeTestThreshold> incomeTestThresholds = new ArrayList<>();
        for (RelationshipStatus relationshipStatus : relationshipStatusService.listAllRelationshipStatuses()) {
            incomeTestThresholds.add(new IncomeTestThreshold(relationshipStatus));
            incomeTestThresholds.add(new IncomeTestThreshold(relationshipStatus));
        }
        incomeTestThresholdGroup.setIncomeTestThresholds(incomeTestThresholds);
        model.addAttribute("incomeTestThresholdGroup", incomeTestThresholdGroup);
        return "incomeTestThresholds/incomeTestThresholds-form";
    }

    @PostMapping
    public String save(@Valid IncomeTestThresholdGroup incomeTestThresholdGroup, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "incomeTestThresholds/incomeTestThresholds-form";
        }
        incomeTestThresholdGroupsService.saveIncomeTestThresholdGroup(incomeTestThresholdGroup);
        return "redirect:/incomeTestThresholds";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("incomeTestThresholdGroup", incomeTestThresholdGroupsService.getIncomeTestThresholdGroupById(id));
        return "incomeTestThresholds/incomeTestThresholds-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        incomeTestThresholdGroupsService.deleteIncomeTestThresholdGroup(id);
        return "redirect:/incomeTestThresholds";
    }
}
