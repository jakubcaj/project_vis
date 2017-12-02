package com.vis.common.domain;

public class Victim {
    private Long id;
    private Profile profile;
    private String Statement;
    private Boolean injured;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getStatement() {
        return Statement;
    }

    public void setStatement(String statement) {
        Statement = statement;
    }

    public Boolean isInjured() {
        return injured;
    }

    public void setInjured(Boolean injured) {
        this.injured = injured;
    }
}
