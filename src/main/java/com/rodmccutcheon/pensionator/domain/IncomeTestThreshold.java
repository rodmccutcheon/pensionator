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
    private BigDecimal threshold;

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

    public BigDecimal getThreshold(){
        return threshold;
    }

    public void setThreshold(BigDecimal threshold) {
        this.threshold = threshold;
    }

    public BigDecimal getReductionRate() {
        return reductionRate;
    }

    public void setReductionRate(BigDecimal reductionRate) {
        this.reductionRate = reductionRate;
    }
}
