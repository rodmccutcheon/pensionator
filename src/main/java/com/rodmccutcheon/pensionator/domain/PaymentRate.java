package com.rodmccutcheon.pensionator.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "payment_rates")
public class PaymentRate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "relationship_status_id")
    @NotNull
    private RelationshipStatus relationshipStatus;

    @NotNull
    private BigDecimal maximumBasicRate;

    @NotNull
    private BigDecimal maximumPensionSupplement;

    @NotNull
    private BigDecimal cleanEnergySupplement;

    public PaymentRate() { }

    public PaymentRate(RelationshipStatus relationshipStatus) {
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

    public BigDecimal getMaximumBasicRate() {
        return maximumBasicRate;
    }

    public void setMaximumBasicRate(BigDecimal maximumBasicRate) {
        this.maximumBasicRate = maximumBasicRate;
    }

    public BigDecimal getMaximumPensionSupplement() {
        return maximumPensionSupplement;
    }

    public void setMaximumPensionSupplement(BigDecimal maximumPensionSupplement) {
        this.maximumPensionSupplement = maximumPensionSupplement;
    }

    public BigDecimal getCleanEnergySupplement() {
        return cleanEnergySupplement;
    }

    public void setCleanEnergySupplement(BigDecimal cleanEnergySupplement) {
        this.cleanEnergySupplement = cleanEnergySupplement;
    }

    public BigDecimal getTotalPayment() {
        return maximumBasicRate
                .add(maximumPensionSupplement)
                .add(cleanEnergySupplement);
    }
}
