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
            String sheetName = language.startsWith("es") ? "mycv-es" : "mycv-en";

            ValueRange response = sheets.spreadsheets().values()
                    .get(spreadsheetId, sheetName + "!A2:M2")
                    .execute();

            List<List<Object>> values = response.getValues();

            if (values == null || values.isEmpty()) {
                throw new RuntimeException("No content found in sheet: " + sheetName);
            }

            List<Object> row = values.get(0);

            // Principal
            String titulo = row.get(0).toString();
            String contenido = row.get(1).toString();
            List<String> menu = Arrays.stream(
                            row.get(2).toString().replace("[", "").replace("]", "").split(","))
                    .map(String::trim)
                    .toList();

            PortfolioPrincipal principal = new PortfolioPrincipal(titulo, contenido, menu);

            // Profile
            String perfilTitulo = row.get(3).toString();
            String perfilSubtitulo = row.get(4).toString();
            String perfilContenido = row.get(5).toString();

            List<String> experienciaOpc = Arrays.stream(
                            row.get(6).toString().replace("[", "").replace("]", "").split(","))
                    .map(String::trim)
                    .toList();

            String experienciaAutoWeb = row.get(7).toString();
            String experienciaAutoMobile = row.get(8).toString();
            String experienciaPerf = row.get(9).toString();
            String experienciaDevFront = row.get(10).toString();
            String experienciaDevBack = row.get(11).toString();

            List<String> conocimientosLib = Arrays.stream(
                            row.get(12).toString().replace("[", "").replace("]", "").split(","))
                    .map(String::trim)
                    .toList();

            PortfolioProfile profile = new PortfolioProfile(
                    perfilTitulo, perfilSubtitulo, perfilContenido,
                    experienciaOpc, experienciaAutoWeb, experienciaAutoMobile,
                    experienciaPerf, experienciaDevFront, experienciaDevBack,
                    conocimientosLib
            );

            return new PortfolioResponse(null, principal, null, profile);

        } catch (Exception e) {
            throw new RuntimeException("Error reading portfolio content from Google Sheets", e);
        }
    }
}