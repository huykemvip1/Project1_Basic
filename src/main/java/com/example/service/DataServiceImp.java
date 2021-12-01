package com.example.service;

import com.example.domain.Account;
import com.example.domain.InformationAccount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public class DataServiceImp implements DataService {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public InformationAccount updateInformationAccount(InformationAccount informationAccount) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        session.saveOrUpdate(informationAccount);
        transaction.commit();
        return informationAccount;
    }

    @Override
    public Account updateAccount(Account account) {
        Session session=sessionFactory.openSession();
        Transaction transaction=session.beginTransaction();
        session.saveOrUpdate(account);
        transaction.commit();
        return account;
    }
}
