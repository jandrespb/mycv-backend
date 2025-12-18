package com.jandcode.mycv.config;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.List;

@Configuration
public class GoogleSheetsConfig {
    private static final String APPLICATION_NAME = "My Resume Backend";

    @Bean
    public Sheets sheetsService() throws Exception {

        InputStream credentialsStream =
                getClass().getClassLoader()
                        .getResourceAsStream("credentials/service-account.json");

        GoogleCredential credential = GoogleCredential.fromStream(credentialsStream)
                .createScoped(List.of("https://www.googleapis.com/auth/spreadsheets"));

        return new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(),
                credential
        )
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}
