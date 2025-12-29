package com.jandcode.mycv.dao.impl;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.jandcode.mycv.dao.CustomerDataSource;
import com.jandcode.mycv.entity.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@Repository
public class GoogleSheetCustomerRepository implements CustomerDataSource {

    private final Sheets sheets;
    private final String spreadsheetId;
    private final String sheetName;

    public GoogleSheetCustomerRepository
            (Sheets sheets,
             @Value("${google.sheets.spreadsheet-id}") String spreadsheetId,
             @Value("${google.sheets.sheet-name}") String sheetName) {
        this.sheets = sheets;
        this.spreadsheetId = spreadsheetId;
        this.sheetName = sheetName;
    }


    @Override
    public long count() {
        try {
            ValueRange response = sheets.spreadsheets().values()
                    .get(spreadsheetId, sheetName + "!A:A")
                    .execute();

            if (response.getValues() == null) {
                return 0;
            }

            // -1 para excluir la cabecera
            return Math.max(0, response.getValues().size() - 1);

        } catch (Exception e) {
            throw new RuntimeException("Error counting rows in Google Sheets", e);
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        try {
            ValueRange response = sheets.spreadsheets().values()
                    .get(spreadsheetId, sheetName + "!C:C")
                    .execute();

            if (response.getValues() == null || response.getValues().size() <= 1) {
                return false;
            }

            // Ignoramos cabecera (fila 0)
            return response.getValues().stream()
                    .skip(1)
                    .map(row -> row.get(0).toString().trim().toLowerCase())
                    .anyMatch(sheetEmail -> sheetEmail.equals(email.trim().toLowerCase()));

        } catch (Exception e) {
            throw new RuntimeException("Error validating email in Google Sheets", e);
        }
    }

    @Override
    public List<Customer> findByIp(String ip) {
        try {
            ValueRange response = sheets.spreadsheets().values()
                    .get(spreadsheetId, sheetName + "!A:F")
                    .execute();

            if (response.getValues() == null || response.getValues().size() <= 1) {
                return Collections.emptyList();
            }

            return response.getValues().stream()
                    .skip(1) // ignorar cabecera
                    .filter(row -> row.size() > 4) // aseguramos columna IP
                    .filter(row -> row.get(4).toString().equals(ip))
                    .map(row -> {
                        Customer c = new Customer();
                        c.setId(row.get(0).toString());
                        c.setCustomer(row.get(1).toString());
                        c.setEmail(row.get(2).toString());
                        c.setMessage(row.get(3).toString());
                        c.setIp(row.get(4).toString());
                        // created_at no es necesario para esta regla
                        return c;
                    })
                    .toList();

        } catch (Exception e) {
            throw new RuntimeException("Error fetching customers by IP from Google Sheets", e);
        }
    }

    @Override
    public List<Customer> findAll() {
        // TODO: mapear todas las filas a Customer
        return Collections.emptyList();
    }

    @Override
    public void save(Customer customer) {
        try {
            List<Object> row = List.of(
                    customer.getId(),
                    customer.getCustomer(),
                    customer.getEmail(),
                    customer.getMessage(),
                    customer.getIp(),
                    customer.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            );

            ValueRange body = new ValueRange()
                    .setValues(List.of(row));

            sheets.spreadsheets().values()
                    .append(spreadsheetId, sheetName + "!A:F", body)
                    .setValueInputOption("RAW")
                    .setInsertDataOption("INSERT_ROWS")
                    .execute();

        } catch (Exception e) {
            throw new RuntimeException("Error saving data to Google Sheets", e);
        }
    }
}
