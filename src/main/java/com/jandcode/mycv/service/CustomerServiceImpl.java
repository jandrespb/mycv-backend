package com.jandcode.mycv.service;

import com.jandcode.mycv.dao.CustomerDataSource;
import com.jandcode.mycv.entity.Customer;
import com.jandcode.mycv.exception.GeneralErrorException;
import com.jandcode.mycv.service.rules.CustomerValidatorService;
import com.jandcode.mycv.utils.IdGeneratorUtil;
import org.springframework.http.HttpStatus;
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
    public void save(Customer customer, String ipAddress) {

        customerValidatorService.validateCustomer(customer);
        customerValidatorService.validateUniqueEmail(customer);
        customerValidatorService.validateIpCustomer(customer, ipAddress);

        customer.setId(IdGeneratorUtil.generateRandomId());
        customer.setIp(ipAddress);

        customerDataSource.save(customer);
    }
}
