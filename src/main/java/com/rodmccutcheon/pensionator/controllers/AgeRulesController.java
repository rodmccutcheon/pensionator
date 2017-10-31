package com.rodmccutcheon.pensionator.controllers;

import com.rodmccutcheon.pensionator.domain.AgeRule;
import com.rodmccutcheon.pensionator.services.AgeRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/ageRules")
public class AgeRulesController {

    private AgeRulesService ageRulesService;

    @Autowired
    public AgeRulesController(AgeRulesService ageRulesService) {
        this.ageRulesService = ageRulesService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("ageRules", ageRulesService.listAllAgeRules());
        return "ageRules/ageRules";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("ageRule", new AgeRule());
        return "ageRules/ageRules-form";
    }

    @PostMapping
    public String save(@Valid AgeRule AgeRule, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "ageRules/ageRules-form";
        }
        ageRulesService.saveAgeRule(AgeRule);
        return "redirect:/ageRules";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("ageRule", ageRulesService.getAgeRuleById(id));
        return "ageRules/ageRules-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        ageRulesService.deleteAgeRule(id);
        return "redirect:/ageRules";
    }
}
