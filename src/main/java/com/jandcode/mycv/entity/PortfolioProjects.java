package com.jandcode.mycv.entity;

import java.util.List;

public class PortfolioProjects {

    private List<String> projectMenu;
    private String projectTitle;
    private String projectCardOneTitle;
    private String projectCardOneDescription;
    private String projectCardOneUrl;
    private String projectCardTwoTitle;
    private String projectCardTwoDescription;
    private String projectCardTwoUrl;
    private String projectCardThreeTitle;
    private String projectCardThreeDescription;
    private String projectCardThreeUrl;
    private String projectCardFourTitle;
    private String projectCardFourDescription;
    private String projectCardFourUrl;
    private String projectCardBtn;

    public PortfolioProjects() {}

    public PortfolioProjects(List<String> projectMenu, String projectTitle, String projectCardOneTitle,
                             String projectCardOneDescription, String projectCardOneUrl, String projectCardTwoTitle,
                             String projectCardTwoDescription, String projectCardTwoUrl, String projectCardThreeTitle,
                             String projectCardThreeDescription, String projectCardThreeUrl, String projectCardFourTitle,
                             String projectCardFourDescription, String projectCardFourUrl, String projectCardBtn) {
        this.projectMenu = projectMenu;
        this.projectTitle = projectTitle;
        this.projectCardOneTitle = projectCardOneTitle;
        this.projectCardOneDescription = projectCardOneDescription;
        this.projectCardOneUrl = projectCardOneUrl;
        this.projectCardTwoTitle = projectCardTwoTitle;
        this.projectCardTwoDescription = projectCardTwoDescription;
        this.projectCardTwoUrl = projectCardTwoUrl;
        this.projectCardThreeTitle = projectCardThreeTitle;
        this.projectCardThreeDescription = projectCardThreeDescription;
        this.projectCardThreeUrl = projectCardThreeUrl;
        this.projectCardFourTitle = projectCardFourTitle;
        this.projectCardFourDescription = projectCardFourDescription;
        this.projectCardFourUrl = projectCardFourUrl;
        this.projectCardBtn = projectCardBtn;
    }

    public List<String> getProjectMenu() {
        return projectMenu;
    }

    public void setProjectMenu(List<String> projectMenu) {
        this.projectMenu = projectMenu;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectCardOneTitle() {
        return projectCardOneTitle;
    }

    public void setProjectCardOneTitle(String projectCardOneTitle) {
        this.projectCardOneTitle = projectCardOneTitle;
    }

    public String getProjectCardOneDescription() {
        return projectCardOneDescription;
    }

    public void setProjectCardOneDescription(String projectCardOneDescription) {
        this.projectCardOneDescription = projectCardOneDescription;
    }

    public String getProjectCardOneUrl() {
        return projectCardOneUrl;
    }

    public void setProjectCardOneUrl(String projectCardOneUrl) {
        this.projectCardOneUrl = projectCardOneUrl;
    }

    public String getProjectCardTwoTitle() {
        return projectCardTwoTitle;
    }

    public void setProjectCardTwoTitle(String projectCardTwoTitle) {
        this.projectCardTwoTitle = projectCardTwoTitle;
    }

    public String getProjectCardTwoDescription() {
        return projectCardTwoDescription;
    }

    public void setProjectCardTwoDescription(String projectCardTwoDescription) {
        this.projectCardTwoDescription = projectCardTwoDescription;
    }

    public String getProjectCardTwoUrl() {
        return projectCardTwoUrl;
    }

    public void setProjectCardTwoUrl(String projectCardTwoUrl) {
        this.projectCardTwoUrl = projectCardTwoUrl;
    }

    public String getProjectCardThreeTitle() {
        return projectCardThreeTitle;
    }

    public void setProjectCardThreeTitle(String projectCardThreeTitle) {
        this.projectCardThreeTitle = projectCardThreeTitle;
    }

    public String getProjectCardThreeDescription() {
        return projectCardThreeDescription;
    }

    public void setProjectCardThreeDescription(String projectCardThreeDescription) {
        this.projectCardThreeDescription = projectCardThreeDescription;
    }

    public String getProjectCardThreeUrl() {
        return projectCardThreeUrl;
    }

    public void setProjectCardThreeUrl(String projectCardThreeUrl) {
        this.projectCardThreeUrl = projectCardThreeUrl;
    }

    public String getProjectCardFourTitle() {
        return projectCardFourTitle;
    }

    public void setProjectCardFourTitle(String projectCardFourTitle) {
        this.projectCardFourTitle = projectCardFourTitle;
    }

    public String getProjectCardFourDescription() {
        return projectCardFourDescription;
    }

    public void setProjectCardFourDescription(String projectCardFourDescription) {
        this.projectCardFourDescription = projectCardFourDescription;
    }

    public String getProjectCardFourUrl() {
        return projectCardFourUrl;
    }

    public void setProjectCardFourUrl(String projectCardFourUrl) {
        this.projectCardFourUrl = projectCardFourUrl;
    }

    public String getProjectCardBtn() {
        return projectCardBtn;
    }

    public void setProjectCardBtn(String projectCardBtn) {
        this.projectCardBtn = projectCardBtn;
    }
}