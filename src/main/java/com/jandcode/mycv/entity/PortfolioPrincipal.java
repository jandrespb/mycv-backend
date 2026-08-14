package com.jandcode.mycv.entity;

import java.util.List;
import java.util.Map;

public class PortfolioPrincipal {

    private String principalTitle;
    private String principalDescription;
    private List<String> principalNav;
    private Map<String, String> principalMessage;

    // Constructor
    public PortfolioPrincipal() {
    }

    public PortfolioPrincipal(String principalTitle, String principalDescription, List<String> principalNav, Map<String, String> principalMessage) {
        this.principalTitle = principalTitle;
        this.principalDescription = principalDescription;
        this.principalNav = principalNav;
        this.principalMessage = principalMessage;
    }

    // Getters and Setters

    public String getPrincipalTitle() {
        return principalTitle;
    }

    public void setPrincipalTitle(String principalTitle) {
        this.principalTitle = principalTitle;
    }

    public String getPrincipalDescription() {
        return principalDescription;
    }

    public void setPrincipalDescription(String principalDescription) {
        this.principalDescription = principalDescription;
    }

    public List<String> getPrincipalNav() {
        return principalNav;
    }

    public void setPrincipalNav(List<String> principalNav) {
        this.principalNav = principalNav;
    }

    public Map<String, String> getPrincipalMessage() {
        return principalMessage;
    }

    public void setPrincipalMessage(Map<String, String> principalMessage) {
        this.principalMessage = principalMessage;
    }
}
