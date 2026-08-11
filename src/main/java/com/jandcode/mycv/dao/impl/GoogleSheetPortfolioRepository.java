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
                    .get(spreadsheetId, sheetName + "!A2:AK")
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

            List<String> profileCards = Arrays.stream(
                            row.get(3).toString().replace("[", "").replace("]", "").split(","))
                    .map(String::trim)
                    .toList();

            String perfilTitulo = row.get(4).toString();
            String perfilSubtitulo = row.get(5).toString();
            String perfilContenido = row.get(6).toString();

            List<String> experienciaOpc = Arrays.stream(
                            row.get(7).toString().replace("[", "").replace("]", "").split(","))
                    .map(String::trim)
                    .toList();

            String experienciaAutoWeb = row.get(8).toString();
            String experienciaAutoMobile = row.get(9).toString();
            String experienciaPerf = row.get(10).toString();
            String experienciaDevFront = row.get(11).toString();
            String experienciaDevBack = row.get(12).toString();

            List<String> conocimientosLib = Arrays.stream(
                            row.get(13).toString().replace("[", "").replace("]", "").split(","))
                    .map(String::trim)
                    .toList();

            PortfolioProfile profile = new PortfolioProfile(
                    profileCards,
                    perfilTitulo, perfilSubtitulo, perfilContenido,
                    experienciaOpc, experienciaAutoWeb, experienciaAutoMobile,
                    experienciaPerf, experienciaDevFront, experienciaDevBack,
                    conocimientosLib
            );

            // Projects
            List<String> projectMenu = Arrays.stream(
                            row.get(14).toString().replace("[", "").replace("]", "")
                                    .split(","))
                                    .map(String::trim)
                                    .toList();

            String projectTitle = row.get(15).toString();
            String projectCardOneTitle = row.get(16).toString();
            String projectCardOneDescription = row.get(17).toString();
            String projectCardOneUrl = row.get(18).toString();
            String projectCardTwoTitle = row.get(19).toString();
            String projectCardTwoDescription = row.get(20).toString();
            String projectCardTwoUrl = row.get(21).toString();
            String projectCardThreeTitle = row.get(22).toString();
            String projectCardThreeDescription = row.get(23).toString();
            String projectCardThreeUrl = row.get(24).toString();
            String projectCardFourTitle = row.get(25).toString();
            String projectCardFourDescription = row.get(26).toString();
            String projectCardFourUrl = row.get(27).toString();
            String projectCardBtn = row.get(28).toString();

            PortfolioProjects projects = new PortfolioProjects(
                    projectMenu, projectTitle, projectCardOneTitle, projectCardOneDescription,
                    projectCardOneUrl, projectCardTwoTitle, projectCardTwoDescription,
                    projectCardTwoUrl, projectCardThreeTitle, projectCardThreeDescription,
                    projectCardThreeUrl, projectCardFourTitle, projectCardFourDescription,
                    projectCardFourUrl, projectCardBtn
            );

            // Contact me
            List<String> contactMenu = Arrays.stream(
                            row.get(29).toString().replace("[", "").replace("]", "")
                                    .split(","))
                    .map(String::trim)
                    .toList();
            String contactTitle = row.get(30).toString();
            String contactDescription = row.get(31).toString();
            String contactEmail = row.get(32).toString();
            String contactFormName = row.get(33).toString();
            String contactFormEmail = row.get(34).toString();
            String contactFormMessage = row.get(35).toString();
            String contactFormBtn = row.get(36).toString();

            PortfolioContact contact = new PortfolioContact(
                    contactMenu, contactTitle, contactDescription, contactEmail,
                    contactFormName, contactFormEmail, contactFormMessage, contactFormBtn
            );

            return new PortfolioResponse(contact, principal, projects, profile);

        } catch (Exception e) {
            throw new RuntimeException("Error reading portfolio content from Google Sheets", e);
        }
    }
}