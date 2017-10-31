package com.rodmccutcheon.pensionator.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "income_streams")
public class IncomeStream {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "income_stream_type_id", nullable = false)
    @NotNull
    private IncomeStreamType incomeStreamType;

    @NotNull
    private String description;

    @NotNull
    private BigDecimal value;

    public IncomeStream() { }

    public IncomeStream(IncomeStreamType incomeStreamType, String description, BigDecimal value) {
        this.incomeStreamType = incomeStreamType;
        this.description = description;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public IncomeStreamType getIncomeStreamType() {
        return incomeStreamType;
    }

    public void setIncomeStreamType(IncomeStreamType incomeStreamType) {
        this.incomeStreamType = incomeStreamType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
