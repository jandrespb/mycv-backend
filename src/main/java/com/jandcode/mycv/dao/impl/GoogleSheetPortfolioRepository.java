package com.jandcode.mycv.dao.impl;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.jandcode.mycv.dao.PortfolioDataSource;
import com.jandcode.mycv.entity.PortfolioContact;
import com.jandcode.mycv.entity.PortfolioPrincipal;
import com.jandcode.mycv.entity.PortfolioProfile;
import com.jandcode.mycv.entity.PortfolioProjects;
import com.jandcode.mycv.entity.PortfolioResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class GoogleSheetPortfolioRepository implements PortfolioDataSource {

    private final Sheets sheets;
    private final String spreadsheetId;

    public GoogleSheetPortfolioRepository(
            Sheets sheets,
            @Value("${google.sheets.spreadsheet-id}") String spreadsheetId) {
        this.sheets = sheets;
        this.spreadsheetId = spreadsheetId;
    }

    @Override
    public PortfolioResponse findContentByLanguage(String language) {
        try {
            // Decide qué pestaña leer según el idioma
            String sheetName = language.startsWith("es") ? "mycv-es" : "mycv-en";

            // Lee la fila 2 (los datos, no la cabecera)
            ValueRange response = sheets.spreadsheets().values()
                    .get(spreadsheetId, sheetName + "!A2:C2")
                    .execute();

            List<List<Object>> values = response.getValues();

            if (values == null || values.isEmpty()) {
                throw new RuntimeException("No content found in sheet: " + sheetName);
            }

            List<Object> row = values.get(0);

            // Mapeo de columnas a Principal
            String principalTitleCol = row.get(0).toString();
            String principalContentCol = row.get(1).toString();
            String principalNavRawCol = row.get(2).toString();

            // Parsear el menu de String a List<String>
            // Formato en sheet: [mi perfil, proyectos, contacto, noo oprimas está!]
            List<String> principalNav = Arrays.stream(
                            principalNavRawCol.replace("[", "").replace("]", "").split(","))
                    .map(String::trim)
                    .toList();

            PortfolioPrincipal principal = new PortfolioPrincipal(principalTitleCol, principalContentCol, principalNav);

            // Las otras secciones por ahora en null
            return new PortfolioResponse(null, principal, null, null);

        } catch (Exception e) {
            throw new RuntimeException("Error reading portfolio content from Google Sheets", e);
        }
    }
}