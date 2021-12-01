package com.example.controller;

import com.example.domain.Account;
import com.example.service.AccountService;
import com.example.service.ServiceError;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final Log log= LogFactory.getLog(HomeController.class);
    //------------- CRudReposity Account
    @Autowired
    private AccountService accountService;
    //------------- Error custom
    @Autowired
    private ServiceError serviceError;
    @GetMapping
    public String home(Model model){
        model.addAttribute("account",new Account());
        return "home";
    }
    @PostMapping
    public String checkLogin(@Valid @ModelAttribute("account") Account account, BindingResult errors
            ,Model model){
        if (errors.hasErrors()){
            return "home";
        }
        String message=serviceError.loginError(account);
        if (message != null) {
            model.addAttribute("message", message);
            return "home";
        }
        return "redirect:/list";
    }
}
