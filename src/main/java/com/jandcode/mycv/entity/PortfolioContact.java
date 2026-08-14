package com.jandcode.mycv.entity;

import java.util.List;
import java.util.Map;

public class PortfolioContact {

    private List<String> contactMenu;
    private String contactTitle;
    private String contactDescription;
    private String contactEmail;
    private String contactFormName;
    private String contactFormEmail;
    private String contactFormMessage;
    private String contactFormBtn;
    private Map<String, String> contactMessage;

    public PortfolioContact() {}

    public PortfolioContact(List<String> contactMenu, String contactTitle, String contactDescription,
                            String contactEmail, String contactFormName, String contactFormEmail,
                            String contactFormMessage, String contactFormBtn, Map<String, String> contactMessage) {
        this.contactMenu = contactMenu;
        this.contactTitle = contactTitle;
        this.contactDescription = contactDescription;
        this.contactEmail = contactEmail;
        this.contactFormName = contactFormName;
        this.contactFormEmail = contactFormEmail;
        this.contactFormMessage = contactFormMessage;
        this.contactFormBtn = contactFormBtn;
        this.contactMessage = contactMessage;
    }

    public List<String> getContactMenu() {
        return contactMenu;
    }

    public void setContactMenu(List<String> contactMenu) {
        this.contactMenu = contactMenu;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getContactDescription() {
        return contactDescription;
    }

    public void setContactDescription(String contactDescription) {
        this.contactDescription = contactDescription;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactFormName() {
        return contactFormName;
    }

    public void setContactFormName(String contactFormName) {
        this.contactFormName = contactFormName;
    }

    public String getContactFormEmail() {
        return contactFormEmail;
    }

    public void setContactFormEmail(String contactFormEmail) {
        this.contactFormEmail = contactFormEmail;
    }

    public String getContactFormMessage() {
        return contactFormMessage;
    }

    public void setContactFormMessage(String contactFormMessage) {
        this.contactFormMessage = contactFormMessage;
    }

    public String getContactFormBtn() {
        return contactFormBtn;
    }

    public void setContactFormBtn(String contactFormBtn) {
        this.contactFormBtn = contactFormBtn;
    }

    public Map<String, String> getContactMessage() {
        return contactMessage;
    }

    public void setContactMessage(Map<String, String> contactMessage) {
        this.contactMessage = contactMessage;
    }
}
