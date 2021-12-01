package com.example.service;

import com.example.domain.Account;
import com.example.domain.InformationAccount;

public interface ServiceError {
    // login error if the password is incorrect
    String loginError(Account account);
    // Registry re-entered password is invalid
    String passwordError(InformationAccount informationAccount,String passwordAgain);
}
