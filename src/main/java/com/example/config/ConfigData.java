package com.example.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class ConfigData {
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        return dataSource;
    }
    private Properties properties(){
        Properties properties = new Properties();
        properties.put("hibernate.show_sql","true");
        properties.put("hibernate.fomart_sql","true");
        properties.put("hibernate.hbm2ddl","update");
        properties.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
        return properties;
    }
    @Bean
    public LocalSessionFactoryBean entityManagerFactory(){
        LocalSessionFactoryBean localSessionFactory=new LocalSessionFactoryBean();
        localSessionFactory.setPackagesToScan("com.example.domain");
        localSessionFactory.setDataSource(dataSource());
        localSessionFactory.setHibernateProperties(properties());
        return localSessionFactory;
    }
    @Bean
    public SessionFactory sessionFactory(){
        return entityManagerFactory().getObject();
    }
    @Bean
    public HibernateTransactionManager transactionManager(){
        HibernateTransactionManager transactionManager=new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory());
        return transactionManager;
    }
}
