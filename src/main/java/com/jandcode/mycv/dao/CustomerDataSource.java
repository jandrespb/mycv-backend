package com.jandcode.mycv.dao;

import com.jandcode.mycv.entity.Customer;

import java.util.List;

public interface CustomerDataSource {

    long count();

    boolean existsByEmail(String email);

    List<Customer> findByIp(String ip);

    List<Customer> findAll();

    void save(Customer customer);
}
