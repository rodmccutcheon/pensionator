package com.rodmccutcheon.pensionator.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "deeming_rates")
public class DeemingRate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "relationship_status_id")
    @NotNull
    private RelationshipStatus relationshipStatus;

    @NotNull
    private BigDecimal thresholdStart;

    private BigDecimal thresholdEnd;

    @NotNull
    private BigDecimal deemingRate;

    @Transient
    private BigDecimal deemedAmount;

    @Transient
    private BigDecimal deemedIncome;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RelationshipStatus getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(RelationshipStatus relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public BigDecimal getThresholdStart() {
        return thresholdStart;
    }

    public void setThresholdStart(BigDecimal thresholdStart) {
        this.thresholdStart = thresholdStart;
    }

    public BigDecimal getThresholdEnd() {
        return thresholdEnd;
    }

    public void setThresholdEnd(BigDecimal thresholdEnd) {
        this.thresholdEnd = thresholdEnd;
    }

    public BigDecimal getDeemingRate() {
        return deemingRate;
    }

    public void setDeemingRate(BigDecimal deemingRate) {
        this.deemingRate = deemingRate;
    }

    public BigDecimal getDeemedAmount() {
        return deemedAmount;
    }

    public void setDeemedAmount(BigDecimal deemedAmount) {
        this.deemedAmount = deemedAmount;
    }

    public BigDecimal getDeemedIncome() {
        return deemedIncome;
    }

    public void setDeemedIncome(BigDecimal deemedIncome) {
        this.deemedIncome = deemedIncome;
    }
}
