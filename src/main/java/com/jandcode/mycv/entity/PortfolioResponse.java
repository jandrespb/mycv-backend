package com.jandcode.mycv.entity;

public class PortfolioResponse {

    private PortfolioContact portfolioContact;
    private PortfolioPrincipal portfolioPrincipal;
    private PortfolioProjects portfolioProjects;
    private PortfolioProfile portfolioProfile;


    public PortfolioResponse() {
    }

    public PortfolioResponse(PortfolioContact portfolioContact, PortfolioPrincipal portfolioPrincipal, PortfolioProjects portfolioProjects, PortfolioProfile portfolioProfile) {
        this.portfolioContact = portfolioContact;
        this.portfolioPrincipal = portfolioPrincipal;
        this.portfolioProjects = portfolioProjects;
        this.portfolioProfile = portfolioProfile;
    }

    public PortfolioContact getPortfolioContact() {
        return portfolioContact;
    }

    public void setPortfolioContact(PortfolioContact portfolioContact) {
        this.portfolioContact = portfolioContact;
    }

    public PortfolioPrincipal getPortfolioPrincipal() {
        return portfolioPrincipal;
    }

    public void setPortfolioPrincipal(PortfolioPrincipal portfolioPrincipal) {
        this.portfolioPrincipal = portfolioPrincipal;
    }

    public PortfolioProjects getPortfolioProjects() {
        return portfolioProjects;
    }

    public void setPortfolioProjects(PortfolioProjects portfolioProjects) {
        this.portfolioProjects = portfolioProjects;
    }

    public PortfolioProfile getPortfolioProfile() {
        return portfolioProfile;
    }

    public void setPortfolioProfile(PortfolioProfile portfolioProfile) {
        this.portfolioProfile = portfolioProfile;
    }
}
