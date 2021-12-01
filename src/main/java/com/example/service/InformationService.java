package com.example.service;

import com.example.domain.InformationAccount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InformationService extends CrudRepository<InformationAccount,String> {
    List<InformationAccount> findAll();
}
