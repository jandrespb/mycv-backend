package com.jandcode.mycv.service;

import com.jandcode.mycv.dao.CustomerDataSource;
import com.jandcode.mycv.entity.Customer;
import com.jandcode.mycv.service.rules.CustomerValidatorService;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService{

    private final CustomerDataSource customerDataSource;
    private final CustomerValidatorService customerValidatorService;

    public CustomerServiceImpl(CustomerDataSource customerDataSource, CustomerValidatorService customerValidatorService) {
        this.customerDataSource = customerDataSource;
        this.customerValidatorService = customerValidatorService;
    }

    @Override
    public Customer save(Customer customer, String ipAddress) {
        // 1️⃣  check business rules
        customerValidatorService.validateCustomer(customer);
        customerValidatorService.validateIpCustomer(customer, ipAddress);
        customerValidatorService.validateUniqueEmail(customer);

        // 2️⃣ Enriquecemos el objeto
        customer.setIp(ipAddress);

        // 3️⃣ Persistence (Google Sheets vía DAO)
        return customerDataSource.save(customer);
    }
}
