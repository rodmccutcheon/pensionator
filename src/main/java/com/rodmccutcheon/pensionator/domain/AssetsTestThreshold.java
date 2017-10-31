package com.rodmccutcheon.pensionator.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "assets_test_thresholds")
public class AssetsTestThreshold {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "relationship_status_id")
    @NotNull
    private RelationshipStatus relationshipStatus;

    @OneToOne
    @JoinColumn(name = "homeowner_status_id")
    @NotNull
    private HomeownerStatus homeownerStatus;

    @NotNull
    private BigDecimal threshold;

    public AssetsTestThreshold() { }

    public AssetsTestThreshold(RelationshipStatus relationshipStatus, HomeownerStatus homeownerStatus) {
        this.relationshipStatus = relationshipStatus;
        this.homeownerStatus = homeownerStatus;
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

    public HomeownerStatus getHomeownerStatus() {
        return homeownerStatus;
    }

    public void setHomeownerStatus(HomeownerStatus homeownerStatus) {
        this.homeownerStatus = homeownerStatus;
    }

    public BigDecimal getThreshold() {
        return threshold;
    }

    public void setThreshold(BigDecimal threshold) {
        this.threshold = threshold;
    }
}
