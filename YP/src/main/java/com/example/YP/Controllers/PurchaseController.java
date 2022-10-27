package com.example.YP.Controllers;

import com.example.YP.Models.*;
import com.example.YP.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Date;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN', 'PURCHASER')")
public class PurchaseController {

    @Autowired
    ProviderCompanyRepository providerCompanyRepository;
    @Autowired
    ProviderContactRepository providerContactRepository;
    @Autowired
    AgreementRepository agreementRepository;
    @Autowired
    PurchaseRepository purchaseRepository;
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/purchase")
    public String main(Model model){
        Iterable<ProviderCompany> listProviders = providerCompanyRepository.findAll();
        model.addAttribute("listProviders", listProviders);
        return "provider/Index";
    }

    @GetMapping("purchase/filter-direct")
    public String Filter(@RequestParam String searchName,
                         Model model){
        Iterable<ProviderCompany> listProviders = providerCompanyRepository.findByName(searchName);
        model.addAttribute("listProviders", listProviders);
        return "provider/Index";
    }

    @GetMapping("purchase/filter-contains")
    public String FilterContains(@RequestParam String searchName,
                                 Model model){
        Iterable<ProviderCompany> listProviders = providerCompanyRepository.findByNameContaining(searchName);
        model.addAttribute("listProviders", listProviders);
        return "provider/Index";
    }
    @GetMapping("purchase/add")
    public String pointAddView(Model model, ProviderCompany providerCompany, ProviderContact contactProvider){
        return "provider/add";
    }
    @PostMapping("purchase/add")
    public String postAdd(@Valid ProviderCompany providerCompany, BindingResult bindingResultProvider,
                          @Valid ProviderContact contactProvider, BindingResult bindingResultContact, Model model){
        if (bindingResultContact.hasFieldErrors("lastname") || bindingResultProvider.hasFieldErrors("name") || bindingResultProvider.hasFieldErrors("place") ||
                bindingResultContact.hasFieldErrors("firstname") || bindingResultContact.hasFieldErrors("number") || bindingResultContact.hasFieldErrors("otch")){
            model.addAttribute("providerCompany", providerCompany);
            model.addAttribute("contactProvider", contactProvider);
            return "provider/add";
        }
        providerCompany.setProviderContact(contactProvider);
        providerCompanyRepository.save(providerCompany);
        return "redirect:/purchase";
    }
    @GetMapping("purchase/detail/{id}")
    public String Detail(@PathVariable() Long id, Model model){
        ProviderCompany company = providerCompanyRepository.findById(id).orElseThrow();
        model.addAttribute("el", company);
        return "provider/company-details";
    }

    @GetMapping("purchase/edit/{id}")
    public String Edit(@PathVariable() Long id, Model model){
        ProviderCompany providerCompany = providerCompanyRepository.findById(id).orElseThrow();
        ProviderContact providerContact = providerContactRepository.findById(providerCompany.getProviderContact().getId()).orElseThrow();
        model.addAttribute("providerCompany", providerCompany);
        model.addAttribute("contactProvider", providerContact);
        return "provider/edit";
    }

    @PostMapping("purchase/edit/{id}")
    public String EditConc(@PathVariable() Long id, @Valid ProviderCompany providerCompany, BindingResult bindingResultProvider,
                           @Valid ProviderContact contactProvider, BindingResult bindingResultContact, Model model){
        if (bindingResultContact.hasFieldErrors("lastname") || bindingResultProvider.hasFieldErrors("name") || bindingResultProvider.hasFieldErrors("place") ||
                bindingResultContact.hasFieldErrors("firstname") || bindingResultContact.hasFieldErrors("number") || bindingResultContact.hasFieldErrors("otch")){
            model.addAttribute("providerCompany", providerCompany);
            model.addAttribute("contactProvider", contactProvider);
            return "provider/edit";
        }
        contactProvider.setId(providerCompanyRepository.findById(id).orElseThrow().getProviderContact().getId());
        providerCompany.setProviderContact(contactProvider);
        providerCompany.setId(id);
        providerCompanyRepository.save(providerCompany);
        return "redirect:/purchase";
    }

    @GetMapping("purchase/del/{id}")
    public String Delete(@PathVariable() Long id){
        providerCompanyRepository.deleteById(id);
        return "redirect:/purchase";
    }

    @GetMapping("purchase/agreement-add/{id}")
    public String agreementAddView(@PathVariable() Long id,Model model){
        ProviderAgreement providerAgreement = new ProviderAgreement();
        providerAgreement.setCompany(providerCompanyRepository.findById(id).orElseThrow());
        providerAgreement.setDate(new Date());
        agreementRepository.save(providerAgreement);
        return "redirect:/purchase/detail/" + id;
    }

    @GetMapping("purchase/agreement-details/{id}")
    public String orderDetails(@PathVariable() Long id, Model model){
        model.addAttribute("el", agreementRepository.findById(id).orElseThrow());
        return "provider/agreement-details";
    }


    @GetMapping("/purchase/agreement-del/{id}")
    public String agreementDelete(@PathVariable() Long id, Model model){
        ProviderAgreement Purchase = agreementRepository.findById(id).orElseThrow();
        agreementRepository.delete(Purchase);
        return "redirect:/purchase/detail/" + Purchase.getCompany().getId();
    }

    @GetMapping("purchase/purchaseproduct-delete/{id}")
    public String purchaseDelete(@PathVariable() Long id, Model model){
        Purchase purchase = purchaseRepository.findById(id).orElseThrow();
        purchaseRepository.delete(purchase);
        return "redirect:/purchase/agreement-details/" + purchase.getProviderAgreement().getId();
    }
    @GetMapping("/purchase/purchaseproduct-add/{id}")
    public String orderProductAddView(@PathVariable() Long id, Model model){
        Iterable<Product> em = productRepository.findAll();
        model.addAttribute("product", em);
        return "provider/purchase-add";
    }
    @PostMapping("/purchase/purchaseproduct-add/{id}")
    public String orderProductAdd(@PathVariable() Long id, @RequestParam(name = "product") Long idem, @RequestParam() int amount, @RequestParam() double cost, Model model){
        if (amount <= 0 || cost <= 0){
            Iterable<Product> em = productRepository.findAll();
            model.addAttribute("product", em);
            model.addAttribute("error", "Проверьте правильность написания кол-ва и суммы закупки");
            return "provider/purchase-add";
        }
        Purchase purchase = new Purchase();
        purchase.setProduct(productRepository.findById(idem).orElseThrow());
        purchase.setProviderAgreement(agreementRepository.findById(id).orElseThrow());
        purchase.setAmount(amount);
        purchase.setCost(cost);
        purchaseRepository.save(purchase);
        return "redirect:/purchase/agreement-details/" + id;
    }
}
