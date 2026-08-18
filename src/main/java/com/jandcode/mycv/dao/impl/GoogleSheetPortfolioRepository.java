package com.jandcode.mycv.dao.impl;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.jandcode.mycv.dao.PortfolioDataSource;
import com.jandcode.mycv.entity.PortfolioContact;
import com.jandcode.mycv.entity.PortfolioPrincipal;
import com.jandcode.mycv.entity.PortfolioProfile;
import com.jandcode.mycv.entity.PortfolioProjects;
import com.jandcode.mycv.entity.PortfolioResponse;
import com.jandcode.mycv.utils.SheetParserUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

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

    // ─── Contenedor principal ─────────────────────────────────────────────────
    @Override
    public PortfolioResponse findContentByLanguage(String language) {
        try {
            String sheetName = language.startsWith("es") ? "mycv-es" : "mycv-en";

            ValueRange response = sheets.spreadsheets().values()
                    .get(spreadsheetId, sheetName + "!A2:R2")
                    .execute();

            List<List<Object>> values = response.getValues();

            if (values == null || values.isEmpty()) {
                throw new RuntimeException("No content found in sheet: " + sheetName);
            }

            List<Object> row = values.get(0);

            return new PortfolioResponse(
                    buildContact(row),
                    buildPrincipal(row),
                    buildProjects(row),
                    buildProfile(row)
            );

        } catch (Exception e) {
            throw new RuntimeException("Error reading portfolio content from Google Sheets", e);
        }
    }

    // ─── Principal (A, B, C) ──────────────────────────────────────────────────
    private PortfolioPrincipal buildPrincipal(List<Object> row) {
        Map<String, String> principalContainer = SheetParserUtils.parseMap(
                row.get(0).toString(), "title", "description"
        );

        List<String> principalNav = SheetParserUtils.parseSimpleList(
                row.get(1).toString()
        );

        Map<String, String> principalMessage = SheetParserUtils.parseMap(
                row.get(2).toString(), "alert", "console"
        );

        return new PortfolioPrincipal(principalContainer, principalNav, principalMessage);
    }

    // ─── Profile (D, E, F, G, H, I, J) ───────────────────────────────────────
    private PortfolioProfile buildProfile(List<Object> row) {
        Map<String, String> profileCardJandToCode = SheetParserUtils.parseMap(
                row.get(3).toString(), "title", "subtitle", "description"
        );

        Map<String, List<Map<String, String>>> profileCardExperience = new LinkedHashMap<>();
        profileCardExperience.put("automation", SheetParserUtils.parseObjectList(
                row.get(4).toString(), "title", "description"
        ));
        profileCardExperience.put("performance", SheetParserUtils.parseObjectList(
                row.get(5).toString(), "title", "description"
        ));
        profileCardExperience.put("devWeb", SheetParserUtils.parseObjectList(
                row.get(6).toString(), "title", "description"
        ));

        Map<String, Object> profileCardKnowledge = new LinkedHashMap<>();
        profileCardKnowledge.put("languages", SheetParserUtils.parseLanguages(
                row.get(7).toString()
        ));
        profileCardKnowledge.put("frameworks", SheetParserUtils.parseSimpleList(
                row.get(8).toString()
        ));
        profileCardKnowledge.put("patternDesigns", SheetParserUtils.parseSimpleList(
                row.get(9).toString()
        ));

        return new PortfolioProfile(profileCardJandToCode, profileCardExperience, profileCardKnowledge);
    }

    // ─── Projects (K, L, M, N) ────────────────────────────────────────────────
    private PortfolioProjects buildProjects(List<Object> row) {
        List<String> projectMenu = SheetParserUtils.parseSimpleList(
                row.get(10).toString()
        );

        Map<String, String> projectContainer = SheetParserUtils.parseMap(
                row.get(11).toString(), "title"
        );

        List<Map<String, String>> projectCards = SheetParserUtils.parseObjectList(
                row.get(12).toString(), "title", "description", "url"
        );

        String projectCardBtn = row.get(13).toString();

        return new PortfolioProjects(projectMenu, projectContainer, projectCards, projectCardBtn);
    }

    // ─── Contact (O, P, Q, R) ─────────────────────────────────────────────────
    private PortfolioContact buildContact(List<Object> row) {
        List<String> contactMenu = SheetParserUtils.parseSimpleList(
                row.get(14).toString()
        );

        Map<String, String> contactContainer = SheetParserUtils.parseMap(
                row.get(15).toString(), "title", "description", "email"
        );

        Map<String, String> formFields = SheetParserUtils.parseMap(
                row.get(16).toString(),
                "placeholderName", "placeholderEmail", "placeholderMessage", "buttonSubmit"
        );

        Map<String, String> contactMessage = SheetParserUtils.parseMap(
                row.get(17).toString(),
                "processing", "success", "error400", "error429",
                "errorGeneral", "processingBtn", "btnText"
        );

        Map<String, Object> contactForm = new LinkedHashMap<>();
        contactForm.put("placeholderName",    formFields.get("placeholderName"));
        contactForm.put("placeholderEmail",   formFields.get("placeholderEmail"));
        contactForm.put("placeholderMessage", formFields.get("placeholderMessage"));
        contactForm.put("buttonSubmit",       formFields.get("buttonSubmit"));
        contactForm.put("contactMessage",     contactMessage);

        return new PortfolioContact(contactMenu, contactContainer, contactForm);
    }
}