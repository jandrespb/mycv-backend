package com.jandcode.mycv.service;

import com.jandcode.mycv.entity.Customer;

public interface CustomerService {
    Customer save(Customer customer, String ipAddress);
}
