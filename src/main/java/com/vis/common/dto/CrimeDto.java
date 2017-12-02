package com.vis.common.dto;

import java.sql.Timestamp;
import java.util.List;

public class CrimeDto {
    private String shortDescription;
    private Timestamp dateCommitted;
    private String description;
    private List<CrimeProfileDto> suspects;
    private List<CrimeProfileDto> victims;

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Timestamp getDateCommitted() {
        return dateCommitted;
    }

    public void setDateCommitted(Timestamp dateCommitted) {
        this.dateCommitted = dateCommitted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CrimeProfileDto> getSuspects() {
        return suspects;
    }

    public void setSuspects(List<CrimeProfileDto> suspects) {
        this.suspects = suspects;
    }

    public List<CrimeProfileDto> getVictims() {
        return victims;
    }

    public void setVictims(List<CrimeProfileDto> victims) {
        this.victims = victims;
    }
}
