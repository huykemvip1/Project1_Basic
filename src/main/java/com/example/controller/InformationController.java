package com.example.controller;

import com.example.domain.Account;
import com.example.domain.InformationAccount;
import com.example.service.AccountService;
import com.example.service.DataService;
import com.example.service.InformationService;
import com.example.service.ServiceError;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/list")
public class InformationController {
    private final Log log= LogFactory.getLog(InformationController.class);
    //------------- CRudReposity Information Account
    @Autowired
    private InformationService informationService;
    //------------- Error custom
    @Autowired
    private ServiceError serviceError;
    //------------ CRudReposity Account
    @Autowired
    private AccountService accountService;
    //----------- query data custom
    @Autowired
    private DataService dataService;

    // print list accounts
    @GetMapping
    public String getInformation(Model model){
        List<InformationAccount> accounts=informationService.findAll();
        List<String> list = new ArrayList<String>();

        List<Integer> STT=new ArrayList<Integer>();
        for (InformationAccount account : accounts){
            list.add(account.getUsername_infor());
        }
        model.addAttribute("accounts",list);
        return "listAccount";
    }
    // get edit
    @GetMapping(value = "/{id}")
    public String updateAccount(@PathVariable(value="id") String username, Model model){
        Optional<InformationAccount> account=informationService.findById(username);
        model.addAttribute("information",account.get());
        return "information";
    }
    // edit information account
    @PostMapping
    public String completeAccount(@Valid @ModelAttribute("information") InformationAccount informationAccount
            , @RequestParam("password_again") String password_again
            , Errors errors, Model model ){
        Account account=new Account();
        account.setUserName(informationAccount.getUsername_infor());
        account.setPassword(informationAccount.getPassword_infor());
        if (errors.hasErrors()){
            return "information";
        }
        String message =serviceError.passwordError(informationAccount,password_again);
        if (message != null){
            model.addAttribute("message",message);
            return "information";
        }
        log.info(informationAccount);
        dataService.updateInformationAccount(informationAccount);
        dataService.updateAccount(account);
        return "redirect:/list";
    }
    // delete account by id
    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable("id") String id){
        informationService.deleteById(id);
        accountService.deleteById(id);
        return "redirect:/list";
    }

}
