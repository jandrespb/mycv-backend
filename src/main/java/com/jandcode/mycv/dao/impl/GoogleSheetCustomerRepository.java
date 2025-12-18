package com.jandcode.mycv.dao.impl;

import com.google.api.services.sheets.v4.Sheets;
import com.jandcode.mycv.config.GoogleSheetsProperties;
import com.jandcode.mycv.dao.CustomerDataSource;
import com.jandcode.mycv.entity.Customer;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class GoogleSheetCustomerRepository implements CustomerDataSource {

    private final Sheets sheets;
    private final GoogleSheetsProperties properties;

    public GoogleSheetCustomerRepository(Sheets sheets, GoogleSheetsProperties properties) {
        this.sheets = sheets;
        this.properties = properties;
    }

    @Override
    public long count() {
        // TODO: leer filas del sheet y devolver cantidad
        return 0;
    }

    @Override
    public boolean existsByEmail(String email) {
        // TODO: recorrer filas y validar email
        return false;
    }

    @Override
    public List<Customer> findByIp(String ip) {
        // TODO: filtrar filas por IP
        return Collections.emptyList();
    }

    @Override
    public List<Customer> findAll() {
        // TODO: mapear todas las filas a Customer
        return Collections.emptyList();
    }

    @Override
    public Customer save(Customer customer) {
        // TODO: insertar nueva fila en Google Sheets
        return customer;
    }
}
