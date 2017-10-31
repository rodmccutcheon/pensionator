package com.rodmccutcheon.pensionator.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "asset_types")
public class AssetType {

    @Id
    private long id;

    private String name;

    private boolean deemed;

    public AssetType() { }

    public AssetType(String name, boolean deemed) {
        this.name = name;
        this.deemed = deemed;
    }

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

    public boolean isDeemed() {
        return deemed;
    }

    public void setDeemed(boolean deemed) {
        this.deemed = deemed;
    }
}
