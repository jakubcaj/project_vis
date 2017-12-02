package com.vis.common.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Crime implements Serializable{
    private Long id;
    private String shortDescription;
    private Timestamp dateCommitted;
    private String description;
    private boolean releasedToPublic;
    private List<Profile> suspects;
    private List<Victim> victims;
    private List<User> officers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public boolean isReleasedToPublic() {
        return releasedToPublic;
    }

    public void setReleasedToPublic(boolean releasedToPublic) {
        this.releasedToPublic = releasedToPublic;
    }

    public List<Profile> getSuspects() {
        return suspects;
    }

    public void setSuspects(List<Profile> suspects) {
        this.suspects = suspects;
    }

    public List<Victim> getVictims() {
        return victims;
    }

    public void setVictims(List<Victim> victims) {
        this.victims = victims;
    }

    public List<User> getOfficers() {
        return officers;
    }

    public void setOfficers(List<User> officers) {
        this.officers = officers;
    }
}
