package com.rodmccutcheon.pensionator.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "income_test_threshold_groups")
public class IncomeTestThresholdGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "income_test_threshold_group_id")
    private List<IncomeTestThreshold> incomeTestThresholds;

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

    public List<IncomeTestThreshold> getIncomeTestThresholds() {
        return incomeTestThresholds;
    }

    public void setIncomeTestThresholds(List<IncomeTestThreshold> incomeTestThresholds) {
        this.incomeTestThresholds = incomeTestThresholds;
    }

    public IncomeTestThreshold getIncomeTestThresholdByRelationshipStatus(RelationshipStatus relationshipStatus) {
        return incomeTestThresholds
                .stream()
                .filter(i -> i.getRelationshipStatus().equals(relationshipStatus))
                .findFirst()
                .get();
    }

}
