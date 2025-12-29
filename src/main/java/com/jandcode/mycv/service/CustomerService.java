package com.jandcode.mycv.service;

import com.jandcode.mycv.entity.Customer;

public interface CustomerService {
    void save(Customer customer, String ipAddress);
}
