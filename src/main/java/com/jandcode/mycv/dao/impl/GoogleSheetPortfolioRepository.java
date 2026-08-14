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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
                    .get(spreadsheetId, sheetName + "!A2:AM")
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

            String rawMessage = row.get(3).toString();
            String[] parts = rawMessage.replace("[", "").replace("]", "").split("\\|");
            Map<String, String> principalMessage = new LinkedHashMap<>();
            principalMessage.put("alert", parts[0].trim());
            principalMessage.put("console", parts[1].trim());

            PortfolioPrincipal principal = new PortfolioPrincipal(titulo, contenido, menu, principalMessage);

            // Profile
            List<String> profileCards = Arrays.stream(
                            row.get(4).toString().replace("[", "").replace("]", "").split(","))
                    .map(String::trim)
                    .toList();

            String perfilTitulo = row.get(5).toString();
            String perfilSubtitulo = row.get(6).toString();
            String perfilContenido = row.get(7).toString();

            List<String> experienciaOpc = Arrays.stream(
                            row.get(8).toString().replace("[", "").replace("]", "").split(","))
                    .map(String::trim)
                    .toList();

            String experienciaAutoWeb = row.get(9).toString();
            String experienciaAutoMobile = row.get(10).toString();
            String experienciaPerf = row.get(11).toString();
            String experienciaDevFront = row.get(12).toString();
            String experienciaDevBack = row.get(13).toString();

            List<String> conocimientosLib = Arrays.stream(
                            row.get(14).toString().replace("[", "").replace("]", "").split(","))
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
                            row.get(15).toString().replace("[", "").replace("]", "")
                                    .split(","))
                    .map(String::trim)
                    .toList();

            String projectTitle = row.get(16).toString();
            String projectCardOneTitle = row.get(17).toString();
            String projectCardOneDescription = row.get(18).toString();
            String projectCardOneUrl = row.get(19).toString();
            String projectCardTwoTitle = row.get(20).toString();
            String projectCardTwoDescription = row.get(21).toString();
            String projectCardTwoUrl = row.get(22).toString();
            String projectCardThreeTitle = row.get(23).toString();
            String projectCardThreeDescription = row.get(24).toString();
            String projectCardThreeUrl = row.get(25).toString();
            String projectCardFourTitle = row.get(26).toString();
            String projectCardFourDescription = row.get(27).toString();
            String projectCardFourUrl = row.get(28).toString();
            String projectCardBtn = row.get(29).toString();

            PortfolioProjects projects = new PortfolioProjects(
                    projectMenu, projectTitle, projectCardOneTitle, projectCardOneDescription,
                    projectCardOneUrl, projectCardTwoTitle, projectCardTwoDescription,
                    projectCardTwoUrl, projectCardThreeTitle, projectCardThreeDescription,
                    projectCardThreeUrl, projectCardFourTitle, projectCardFourDescription,
                    projectCardFourUrl, projectCardBtn
            );

            // Contact me
            List<String> contactMenu = Arrays.stream(
                            row.get(30).toString().replace("[", "").replace("]", "")
                                    .split(","))
                    .map(String::trim)
                    .toList();
            String contactTitle = row.get(31).toString();
            String contactDescription = row.get(32).toString();
            String contactEmail = row.get(33).toString();
            String contactFormName = row.get(34).toString();
            String contactFormEmail = row.get(35).toString();
            String contactFormMessage = row.get(36).toString();
            String contactFormBtn = row.get(37).toString();

            String rawContactMessage = row.get(38).toString();
            String[] contactParts = rawContactMessage.replace("[", "").replace("]", "").split("\\|");
            Map<String, String> contactMessage = new LinkedHashMap<>();
            contactMessage.put("processing", contactParts[0].trim());
            contactMessage.put("success", contactParts[1].trim());
            contactMessage.put("error400", contactParts[2].trim());
            contactMessage.put("error429", contactParts[3].trim());
            contactMessage.put("errorGeneral", contactParts[4].trim());
            contactMessage.put("processingBtn", contactParts[5].trim());
            contactMessage.put("btnText", contactParts[6].trim());

            PortfolioContact contact = new PortfolioContact(
                    contactMenu, contactTitle, contactDescription, contactEmail,
                    contactFormName, contactFormEmail, contactFormMessage, contactFormBtn,
                    contactMessage
            );

            return new PortfolioResponse(contact, principal, projects, profile);

        } catch (Exception e) {
            throw new RuntimeException("Error reading portfolio content from Google Sheets", e);
        }
    }
}