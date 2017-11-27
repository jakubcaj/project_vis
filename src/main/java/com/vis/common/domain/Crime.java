package com.vis.common.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Crime implements Serializable{
    private Long id;
    private Date dateCommited;
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

    public Date getDateCommited() {
        return dateCommited;
    }

    public void setDateCommited(Date dateCommited) {
        this.dateCommited = dateCommited;
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
