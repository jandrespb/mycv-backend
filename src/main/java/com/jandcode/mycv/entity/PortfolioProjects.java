package com.jandcode.mycv.entity;

import java.util.List;
import java.util.Map;

public class PortfolioProjects {

    private List<String> projectMenu;
    private Map<String, String> projectContainer;
    private List<Map<String, String>> projectCards;
    private String projectCardBtn;

    public PortfolioProjects() {}

    public PortfolioProjects(List<String> projectMenu, Map<String, String> projectContainer,
                             List<Map<String, String>> projectCards, String projectCardBtn) {
        this.projectMenu = projectMenu;
        this.projectContainer = projectContainer;
        this.projectCards = projectCards;
        this.projectCardBtn = projectCardBtn;
    }

    public List<String> getProjectMenu() {
        return projectMenu;
    }

    public void setProjectMenu(List<String> projectMenu) {
        this.projectMenu = projectMenu;
    }

    public Map<String, String> getProjectContainer() {
        return projectContainer;
    }

    public void setProjectContainer(Map<String, String> projectContainer) {
        this.projectContainer = projectContainer;
    }

    public List<Map<String, String>> getProjectCards() {
        return projectCards;
    }

    public void setProjectCards(List<Map<String, String>> projectCards) {
        this.projectCards = projectCards;
    }

    public String getProjectCardBtn() {
        return projectCardBtn;
    }

    public void setProjectCardBtn(String projectCardBtn) {
        this.projectCardBtn = projectCardBtn;
    }
}