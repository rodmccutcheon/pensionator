package com.rodmccutcheon.pensionator.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "deeming_rate_groups")
public class DeemingRateGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "deeming_rate_group_id")
    private List<DeemingRate> deemingRates;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @DateTimeFormat(pattern="dd MMMM yyyy")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @DateTimeFormat(pattern="dd MMMM yyyy")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<DeemingRate> getDeemingRates() {
        return deemingRates;
    }

    public void setDeemingRates(List<DeemingRate> deemingRates) {
        this.deemingRates = deemingRates;
    }

    public List<DeemingRate> getDeemingRatesByRelationshipStatus(RelationshipStatus relationshipStatus) {
        return deemingRates
                .stream()
                .filter(d -> d.getRelationshipStatus().equals(relationshipStatus))
                .collect(Collectors.toList());
    }
}
