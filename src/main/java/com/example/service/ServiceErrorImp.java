package com.example.service;

import com.example.domain.Account;
import com.example.domain.InformationAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Optional;

@Service
public class ServiceErrorImp implements ServiceError{
    @Autowired
    private AccountService accountService;
    @Override
    public String loginError(Account account) {
        Optional<Account> accountSS=accountService.findById(account.getUserName());
        if (accountSS.isEmpty() || !accountSS.get().getPassword().equals(account.getPassword())){
            return "The account is incorrect";
        }
        return null;
    }

    @Override
    public String passwordError(InformationAccount informationAccount, String passwordAgain) {
        if (!informationAccount.getPassword_infor().equals(passwordAgain)){
            return "Wrong password re-entered";
        }
        return null;
    }
}
