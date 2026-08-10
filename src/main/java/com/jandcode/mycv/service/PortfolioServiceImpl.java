package com.jandcode.mycv.service;

import com.jandcode.mycv.dao.PortfolioDataSource;
import com.jandcode.mycv.entity.PortfolioResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    private final PortfolioDataSource portfolioDataSource;

    private PortfolioResponse contentEs;
    private PortfolioResponse contentEn;

    public PortfolioServiceImpl(PortfolioDataSource portfolioDataSource) {
        this.portfolioDataSource = portfolioDataSource;
    }

    @PostConstruct
    public void loadContent() {
        contentEs = portfolioDataSource.findContentByLanguage("es");
        contentEn = portfolioDataSource.findContentByLanguage("en");
    }

    @Override
    public PortfolioResponse findContentByLanguage(String language) {
        return language.startsWith("es") ? contentEs : contentEn;
    }
}