package com.example.service;

import com.example.domain.Account;
import com.example.domain.InformationAccount;

public interface DataService {
    // save information account modified
    InformationAccount updateInformationAccount(InformationAccount informationAccount);
    // save account modified
    Account updateAccount(Account account);
}
