package com.rodmccutcheon.pensionator.controllers;

import com.rodmccutcheon.pensionator.domain.Couple;
import com.rodmccutcheon.pensionator.services.ClientService;
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

@Controller
@RequestMapping("/clients")
public class ClientController {

    private ClientService clientService;
    private RelationshipStatusService relationshipStatusService;
    private HomeownerStatusesService homeownerStatusesService;

    @Autowired
    public ClientController(ClientService clientService,
                            RelationshipStatusService relationshipStatusService,
                            HomeownerStatusesService homeownerStatusesService) {
        this.clientService = clientService;
        this.relationshipStatusService = relationshipStatusService;
        this.homeownerStatusesService = homeownerStatusesService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("clients", clientService.listAllClients());
        return "clients/clients";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("couple", new Couple());
        //model.addAttribute("partner", new Client());
        model.addAttribute("relationshipStatuses", relationshipStatusService.listAllRelationshipStatuses());
        model.addAttribute("homeownerStatuses", homeownerStatusesService.listAllHomeownerStatuses());
        return "clients/client-form";
    }

    @PostMapping
    public String save(@Valid Couple couple, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("relationshipStatuses", relationshipStatusService.listAllRelationshipStatuses());
            model.addAttribute("homeownerStatuses", homeownerStatusesService.listAllHomeownerStatuses());
            return "clients/client-form";
        }
        clientService.saveClient(couple.getClient());
        if (couple.getPartner().getFirstName() != "") {
            couple.getPartner().setPartner(couple.getClient());
            clientService.saveClient(couple.getPartner());
            couple.getClient().setPartner(couple.getPartner());
            clientService.saveClient(couple.getClient());
        }
        return "redirect:/clients/" + couple.getClient().getId();
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("client", clientService.getClientById(id));
        return "clients/client-show";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("client", clientService.getClientById(id));
        model.addAttribute("relationshipStatuses", relationshipStatusService.listAllRelationshipStatuses());
        model.addAttribute("homeownerStatuses", homeownerStatusesService.listAllHomeownerStatuses());
        return "clients/client-form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        clientService.deleteClient(id);
        return "redirect:/clients";
    }
}
