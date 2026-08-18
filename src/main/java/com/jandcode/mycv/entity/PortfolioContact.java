package com.jandcode.mycv.entity;

import java.util.List;
import java.util.Map;

public class PortfolioContact {

    private List<String> contactMenu;
    private Map<String, String> contactContainer;
    private Map<String, Object> contactForm;

    public PortfolioContact() {}

    public PortfolioContact(List<String> contactMenu, Map<String, String> contactContainer,
                            Map<String, Object> contactForm) {
        this.contactMenu = contactMenu;
        this.contactContainer = contactContainer;
        this.contactForm = contactForm;
    }

    public List<String> getContactMenu() {
        return contactMenu;
    }

    public void setContactMenu(List<String> contactMenu) {
        this.contactMenu = contactMenu;
    }

    public Map<String, String> getContactContainer() {
        return contactContainer;
    }

    public void setContactContainer(Map<String, String> contactContainer) {
        this.contactContainer = contactContainer;
    }

    public Map<String, Object> getContactForm() {
        return contactForm;
    }

    public void setContactForm(Map<String, Object> contactForm) {
        this.contactForm = contactForm;
    }
}
