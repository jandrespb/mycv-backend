package com.jandcode.mycv.config;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
public class GoogleSheetsConfig {

    private static final String APPLICATION_NAME = "mycv";

    @Bean
    public Sheets sheetsService(GoogleSheetsProperties properties) throws Exception {


        String credentialsJson = System.getenv("GOOGLE_SHEETS_CREDENTIALS");

        if (credentialsJson == null || credentialsJson.isEmpty()) {
            throw new IllegalStateException("GOOGLE_SHEETS_CREDENTIALS not set");
        }

        InputStream credentialsStream = new ByteArrayInputStream(
                credentialsJson.getBytes(StandardCharsets.UTF_8)
        );

        GoogleCredentials credentials = GoogleCredentials
                .fromStream(credentialsStream)
                .createScoped(List.of(SheetsScopes.SPREADSHEETS));

        return new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                GsonFactory.getDefaultInstance(),
                new HttpCredentialsAdapter(credentials)
        )
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}

