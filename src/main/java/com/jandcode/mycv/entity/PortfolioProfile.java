package com.jandcode.mycv.entity;

import java.util.List;

public class PortfolioProfile {

    private List<String> profileCards;
    private String jandtocodeTitle;
    private String jandtocodeSubtitle;
    private String jandtocodeContent;
    private List<String> experienceOptions;
    private String experienceAutoWeb;
    private String experienceAutoMobile;
    private String experiencePerformance;
    private String experienceDevFront;
    private String experienceDevBack;
    private List<String> knowledgeLib;

    public PortfolioProfile() {
    }

    public PortfolioProfile(List<String> profileCards, String jandtocodeTitle, String jandtocodeSubtitle, String jandtocodeContent,
                            List<String> experienceOptions, String experienceAutoWeb, String experienceAutoMobile,
                            String experiencePerformance, String experienceDevFront, String experienceDevBack,
                            List<String> knowledgeLib) {
        this.profileCards = profileCards;
        this.jandtocodeTitle = jandtocodeTitle;
        this.jandtocodeSubtitle = jandtocodeSubtitle;
        this.jandtocodeContent = jandtocodeContent;
        this.experienceOptions = experienceOptions;
        this.experienceAutoWeb = experienceAutoWeb;
        this.experienceAutoMobile = experienceAutoMobile;
        this.experiencePerformance = experiencePerformance;
        this.experienceDevFront = experienceDevFront;
        this.experienceDevBack = experienceDevBack;
        this.knowledgeLib = knowledgeLib;
    }

    public List<String> getProfileCards() {
        return profileCards;
    }

    public void setProfileCards(List<String> profileCards) {
        this.profileCards = profileCards;
    }

    public String getJandtocodeTitle() {
        return jandtocodeTitle;
    }

    public void setJandtocodeTitle(String jandtocodeTitle) {
        this.jandtocodeTitle = jandtocodeTitle;
    }

    public String getJandtocodeSubtitle() {
        return jandtocodeSubtitle;
    }

    public void setJandtocodeSubtitle(String jandtocodeSubtitle) {
        this.jandtocodeSubtitle = jandtocodeSubtitle;
    }

    public String getJandtocodeContent() {
        return jandtocodeContent;
    }

    public void setJandtocodeContent(String jandtocodeContent) {
        this.jandtocodeContent = jandtocodeContent;
    }

    public List<String> getExperienceOptions() {
        return experienceOptions;
    }

    public void setExperienceOptions(List<String> experienceOptions) {
        this.experienceOptions = experienceOptions;
    }

    public String getExperienceAutoWeb() {
        return experienceAutoWeb;
    }

    public void setExperienceAutoWeb(String experienceAutoWeb) {
        this.experienceAutoWeb = experienceAutoWeb;
    }

    public String getExperienceAutoMobile() {
        return experienceAutoMobile;
    }

    public void setExperienceAutoMobile(String experienceAutoMobile) {
        this.experienceAutoMobile = experienceAutoMobile;
    }

    public String getExperiencePerformance() {
        return experiencePerformance;
    }

    public void setExperiencePerformance(String experiencePerformance) {
        this.experiencePerformance = experiencePerformance;
    }

    public String getExperienceDevFront() {
        return experienceDevFront;
    }

    public void setExperienceDevFront(String experienceDevFront) {
        this.experienceDevFront = experienceDevFront;
    }

    public String getExperienceDevBack() {
        return experienceDevBack;
    }

    public void setExperienceDevBack(String experienceDevBack) {
        this.experienceDevBack = experienceDevBack;
    }

    public List<String> getKnowledgeLib() {
        return knowledgeLib;
    }

    public void setKnowledgeLib(List<String> knowledgeLib) {
        this.knowledgeLib = knowledgeLib;
    }
}
