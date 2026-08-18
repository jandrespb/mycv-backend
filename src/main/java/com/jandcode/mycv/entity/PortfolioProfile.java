package com.jandcode.mycv.entity;

import java.util.List;
import java.util.Map;

public class PortfolioProfile {

    private Map<String, String> profileCardJandToCode;
    private Map<String, List<Map<String, String>>> profileCardExperience;
    private Map<String, Object> profileCardKnowledge;

    public PortfolioProfile() {
    }

    public PortfolioProfile(Map<String, String> profileCardJandToCode, Map<String, List<Map<String,
            String>>> profileCardExperience, Map<String, Object> profileCardKnowledge) {
        this.profileCardJandToCode = profileCardJandToCode;
        this.profileCardExperience = profileCardExperience;
        this.profileCardKnowledge = profileCardKnowledge;
    }

    public Map<String, String> getProfileCardJandToCode() {
        return profileCardJandToCode;
    }

    public void setProfileCardJandToCode(Map<String, String> profileCardJandToCode) {
        this.profileCardJandToCode = profileCardJandToCode;
    }

    public Map<String, List<Map<String, String>>> getProfileCardExperience() {
        return profileCardExperience;
    }

    public void setProfileCardExperience(Map<String, List<Map<String, String>>> profileCardExperience) {
        this.profileCardExperience = profileCardExperience;
    }

    public Map<String, Object> getProfileCardKnowledge() {
        return profileCardKnowledge;
    }

    public void setProfileCardKnowledge(Map<String, Object> profileCardKnowledge) {
        this.profileCardKnowledge = profileCardKnowledge;
    }
}
