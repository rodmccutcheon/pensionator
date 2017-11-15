package com.rodmccutcheon.pensionator.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "homeowner_status")
public class HomeownerStatus {

    @Id
    private long id;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HomeownerStatus() { }

    public HomeownerStatus(String name) {
        this.name = name;
    }
}
