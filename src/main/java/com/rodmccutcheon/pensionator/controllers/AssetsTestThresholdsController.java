package com.rodmccutcheon.pensionator.controllers;

import com.rodmccutcheon.pensionator.domain.*;
import com.rodmccutcheon.pensionator.services.AssetsTestThresholdGroupsService;
import com.rodmccutcheon.pensionator.services.HomeownerStatusesService;
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
@RequestMapping("/assetsTestThresholds")
public class AssetsTestThresholdsController {

    private AssetsTestThresholdGroupsService assetsTestThresholdGroupsService;
    private RelationshipStatusService relationshipStatusService;
    private HomeownerStatusesService homeownerStatusesService;

    @Autowired
    public AssetsTestThresholdsController(AssetsTestThresholdGroupsService assetsTestThresholdGroupsService,
                                          RelationshipStatusService relationshipStatusService,
                                          HomeownerStatusesService homeownerStatusesService) {
        this.assetsTestThresholdGroupsService = assetsTestThresholdGroupsService;
        this.relationshipStatusService = relationshipStatusService;
        this.homeownerStatusesService = homeownerStatusesService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("assetsTestThresholdGroups", assetsTestThresholdGroupsService.listAllAssetsTestThresholdGroups());
        return "assetsTestThresholds/assetsTestThresholds";
    }

    @GetMapping("/new")
    public String create(Model model) {
        AssetsTestThresholdGroup assetsTestThresholdGroup = new AssetsTestThresholdGroup();
        List<AssetsTestThreshold> assetsTestThresholds = new ArrayList<>();
        for (RelationshipStatus relationshipStatus : relationshipStatusService.listAllRelationshipStatuses()) {
            for (HomeownerStatus homeownerStatus : homeownerStatusesService.listAllHomeownerStatuses()) {
                assetsTestThresholds.add(new AssetsTestThreshold(relationshipStatus, homeownerStatus));
            }
        }
        assetsTestThresholdGroup.setAssetsTestThresholds(assetsTestThresholds);
        model.addAttribute("assetsTestThresholdGroup", assetsTestThresholdGroup);
        return "assetsTestThresholds/assetsTestThresholds-form";
    }

    @PostMapping
    public String save(@Valid AssetsTestThresholdGroup assetsTestThresholdGroup, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "assetsTestThresholds/assetsTestThresholds-form";
        }
        assetsTestThresholdGroupsService.saveAssetsTestThresholdGroup(assetsTestThresholdGroup);
        return "redirect:/assetsTestThresholds";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("assetsTestThresholdGroup", assetsTestThresholdGroupsService.getAssetsTestThresholdGroupById(id));
        return "assetsTestThresholds/assetsTestThresholds-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        assetsTestThresholdGroupsService.deleteAssetsTestThresholdGroup(id);
        return "redirect:/assetsTestThresholds";
    }
}
