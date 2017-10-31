package com.rodmccutcheon.pensionator.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "income_test_thresholds")
public class IncomeTestThreshold {

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

    private BigDecimal reductionRate;

    public IncomeTestThreshold() { }

    public IncomeTestThreshold(RelationshipStatus relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

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

    public BigDecimal getReductionRate() {
        return reductionRate;
    }

    public void setReductionRate(BigDecimal reductionRate) {
        this.reductionRate = reductionRate;
    }
}
