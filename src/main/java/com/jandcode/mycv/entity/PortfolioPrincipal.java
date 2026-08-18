package com.jandcode.mycv.entity;

import java.util.List;
import java.util.Map;

public class PortfolioPrincipal {

    private Map<String, String> principalContainer;
    private List<String> principalNav;
    private Map<String, String> principalMessage;

    public PortfolioPrincipal() {}

    public PortfolioPrincipal(Map<String, String> principalContainer, List<String> principalNav, Map<String, String> principalMessage) {
        this.principalContainer = principalContainer;
        this.principalNav = principalNav;
        this.principalMessage = principalMessage;
    }

    public Map<String, String> getPrincipalContainer() {
        return principalContainer;
    }

    public void setPrincipalContainer(Map<String, String> principalContainer) {
        this.principalContainer = principalContainer;
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
