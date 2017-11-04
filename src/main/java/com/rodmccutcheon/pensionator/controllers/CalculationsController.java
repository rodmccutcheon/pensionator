package com.rodmccutcheon.pensionator.controllers;

import com.rodmccutcheon.pensionator.domain.*;
import com.rodmccutcheon.pensionator.domain.exceptions.CalculationException;
import com.rodmccutcheon.pensionator.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.LinkedHashSet;

@Controller
@RequestMapping("/clients/{clientId}/calculations")
public class CalculationsController {

    private ClientService clientService;
    private CalculationService calculationService;
    private AssetTypesService assetTypesService;
    private IncomeStreamTypesService incomeStreamTypesService;
    private IncomeTestThresholdGroupsService incomeTestThresholdGroupsService;
    private AssetsTestThresholdGroupsService assetsTestThresholdGroupsService;
    private DeemingRateGroupsService deemingRateGroupsService;
    private PaymentRateGroupsService paymentRateGroupsService;

    @Autowired
    public CalculationsController(ClientService clientService,
                                  CalculationService calculationService,
                                  AssetTypesService assetTypesService,
                                  IncomeStreamTypesService incomeStreamTypesService,
                                  IncomeTestThresholdGroupsService incomeTestThresholdGroupsService,
                                  AssetsTestThresholdGroupsService assetsTestThresholdGroupsService,
                                  DeemingRateGroupsService deemingRateGroupsService,
                                  PaymentRateGroupsService paymentRateGroupsService) {
        this.clientService = clientService;
        this.calculationService = calculationService;
        this.assetTypesService = assetTypesService;
        this.incomeStreamTypesService = incomeStreamTypesService;
        this.incomeTestThresholdGroupsService = incomeTestThresholdGroupsService;
        this.assetsTestThresholdGroupsService = assetsTestThresholdGroupsService;
        this.deemingRateGroupsService = deemingRateGroupsService;
        this.paymentRateGroupsService = paymentRateGroupsService;
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long clientId, @PathVariable Long id, Model model) {
        Calculation calculation = null;
        try {
            calculation = calculate(calculationService.getCalculationById(id));
        } catch (CalculationException e) {
            model.addAttribute("client", clientService.getClientById(clientId));
            model.addAttribute("error", e.getMessage());
            return "clients/client-show";
        }
        model.addAttribute(calculation);
        return "clients/client-calculation-show";
    }

    @GetMapping("/new")
    public String create(@PathVariable Long clientId, Model model) {
        Client client = clientService.getClientById(clientId);
        Calculation calculation = new Calculation();
        calculation.setClient(client);
        calculation.setAssets(new LinkedHashSet<>(Arrays.asList(new Asset())));
        calculation.setIncomeStreams(new LinkedHashSet<>(Arrays.asList(new IncomeStream())));
        model.addAttribute("calculation", calculation);
        model.addAttribute("assetTypes", assetTypesService.listAllAssetTypes());
        model.addAttribute("incomeStreamTypes", incomeStreamTypesService.listAllIncomeStreamTypes());
        return "clients/client-calculation";
    }

    @PostMapping(params={"addAsset"})
    public String addAsset(Calculation calculation, final BindingResult bindingResult, Model model) {
        calculation.getAssets().add(new Asset());
        model.addAttribute("assetTypes", assetTypesService.listAllAssetTypes());
        model.addAttribute("incomeStreamTypes", incomeStreamTypesService.listAllIncomeStreamTypes());
        return "clients/client-calculation";
    }

    @PostMapping(params={"removeAsset"})
    public String removeAsset(Calculation calculation, final BindingResult bindingResult, Model model, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeAsset"));
        calculation.getAssets().remove(rowId.intValue());
        model.addAttribute("assetTypes", assetTypesService.listAllAssetTypes());
        model.addAttribute("incomeStreamTypes", incomeStreamTypesService.listAllIncomeStreamTypes());
        return "clients/client-calculation";
    }

    @PostMapping(params={"addIncomeStream"})
    public String addIncomeStream(final Calculation calculation, final BindingResult bindingResult, Model model) {
        calculation.getIncomeStreams().add(new IncomeStream());
        model.addAttribute("assetTypes", assetTypesService.listAllAssetTypes());
        model.addAttribute("incomeStreamTypes", incomeStreamTypesService.listAllIncomeStreamTypes());
        return "clients/client-calculation";
    }

    @PostMapping(params={"removeIncomeStream"})
    public String removeIncomeStream(Calculation calculation, final BindingResult bindingResult, Model model, final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeIncomeStream"));
        calculation.getIncomeStreams().remove(rowId.intValue());
        model.addAttribute("assetTypes", assetTypesService.listAllAssetTypes());
        model.addAttribute("incomeStreamTypes", incomeStreamTypesService.listAllIncomeStreamTypes());
        return "clients/client-calculation";
    }

    @PostMapping
    public String save(@PathVariable Long clientId, @Valid Calculation calculation, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("assetTypes", assetTypesService.listAllAssetTypes());
            model.addAttribute("incomeStreamTypes", incomeStreamTypesService.listAllIncomeStreamTypes());
            return "clients/client-calculation";
        }
        Client client = clientService.getClientById(clientId);
        calculation.setClient(client);
        try {
            calculate(calculation);
        } catch (CalculationException e) {
            model.addAttribute("error", e.getMessage());
            return "clients/client-calculation";
        }
        return "redirect:/clients/" + clientId + "/calculations/" + calculation.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("calculation", calculationService.getCalculationById(id));
        model.addAttribute("assetTypes", assetTypesService.listAllAssetTypes());
        model.addAttribute("incomeStreamTypes", incomeStreamTypesService.listAllIncomeStreamTypes());
        return "clients/client-calculation";
    }

    @GetMapping("/clone/{id}")
    public String clone(@PathVariable Long clientId, @PathVariable Long id) {
        calculationService.cloneCalculation(calculationService.getCalculationById(id));
        return "redirect:/clients/" + clientId;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long clientId, @PathVariable Long id) {
        calculationService.deleteCalculation(id);
        return "redirect:/clients/" + clientId;
    }

    private Calculation calculate(Calculation calculation) throws CalculationException {
        IncomeTestThresholdGroup i = incomeTestThresholdGroupsService.getIncomeTestThresholdGroupByDate(calculation.getDate());
        AssetsTestThresholdGroup a = assetsTestThresholdGroupsService.getAssetsTestThresholdGroupByDate(calculation.getDate());
        DeemingRateGroup d = deemingRateGroupsService.getDeemingRateGroupByDate(calculation.getDate());
        PaymentRateGroup p = paymentRateGroupsService.getPaymentRateGroupByDate(calculation.getDate());
        calculation.calculatePayment(i, a, d, p);
        return calculationService.saveCalculation(calculation);
    }

}
