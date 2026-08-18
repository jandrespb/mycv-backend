package com.jandcode.mycv.entity;

import java.util.List;
import java.util.Map;

public class PortfolioProfile {

    private List<String> profileNameCards;
    private Map<String, String> profileCardJandToCode;
    private Map<String, Object> profileCardExperience;
    private Map<String, Object> profileCardKnowledge;

    public PortfolioProfile() {
    }

    public PortfolioProfile(List<String> profileNameCards, Map<String, String> profileCardJandToCode, Map<String, Object> profileCardExperience, Map<String, Object> profileCardKnowledge) {
        this.profileNameCards = profileNameCards;
        this.profileCardJandToCode = profileCardJandToCode;
        this.profileCardExperience = profileCardExperience;
        this.profileCardKnowledge = profileCardKnowledge;
    }

    public List<String> getProfileNameCards() {
        return profileNameCards;
    }

    public void setProfileNameCards(List<String> profileNameCards) {
        this.profileNameCards = profileNameCards;
    }

    public Map<String, String> getProfileCardJandToCode() {
        return profileCardJandToCode;
    }

    public void setProfileCardJandToCode(Map<String, String> profileCardJandToCode) {
        this.profileCardJandToCode = profileCardJandToCode;
    }

    public Map<String, Object> getProfileCardExperience() {
        return profileCardExperience;
    }

    public void setProfileCardExperience(Map<String, Object> profileCardExperience) {
        this.profileCardExperience = profileCardExperience;
    }

    public Map<String, Object> getProfileCardKnowledge() {
        return profileCardKnowledge;
    }

    public void setProfileCardKnowledge(Map<String, Object> profileCardKnowledge) {
        this.profileCardKnowledge = profileCardKnowledge;
    }
}
