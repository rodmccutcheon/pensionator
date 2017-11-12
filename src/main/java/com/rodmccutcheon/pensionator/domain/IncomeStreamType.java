package com.rodmccutcheon.pensionator.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "income_stream_types")
public class IncomeStreamType {

    @Id
    @NotNull
    private long id;

    @NotNull
    private String name;

    @NotNull
    private boolean asset;

    public IncomeStreamType() { }

    public IncomeStreamType(String name) {
        this.name = name;
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

    public boolean isAsset() {
        return asset;
    }

    public void setAsset(boolean asset) {
        this.asset = asset;
    }
}
