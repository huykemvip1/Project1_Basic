package com.example.controller;

import com.example.domain.Account;
import com.example.domain.InformationAccount;
import com.example.service.AccountService;
import com.example.service.InformationService;
import com.example.service.ServiceError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {
    //------------- CRudReposity Information Account
    @Autowired
    private InformationService informationService;
    //------------- CRudReposity Account
    @Autowired
    private AccountService accountService;
    //------------- Error custom
    @Autowired
    private ServiceError serviceError;
    @GetMapping
    public String register(Model model){
        model.addAttribute("information", new InformationAccount());
        return "register";
    }
    @PostMapping
    public String save(@Valid @ModelAttribute("information") InformationAccount informationAccount
            , @RequestParam("password_again") String password_again, Errors errors,Model model){
        Account account=new Account();
        account.setUserName(informationAccount.getUsername_infor());
        account.setPassword(informationAccount.getPassword_infor());
        if (errors.hasErrors()){
            return "register";
        }
        String message =serviceError.passwordError(informationAccount,password_again);
        if (message != null){
            model.addAttribute("message",message);
            return "register";
        }
        informationService.save(informationAccount);
        accountService.save(account);
        return "redirect:/home";
    }

}
