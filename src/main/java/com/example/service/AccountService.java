package com.example.service;

import com.example.domain.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface AccountService extends CrudRepository<Account,String> {
}
