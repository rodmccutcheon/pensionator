package com.rodmccutcheon.pensionator.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
@Table(name = "assets_test_threshold_groups")
public class AssetsTestThresholdGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private Date startDate;

    @NotNull
    private Date endDate;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "assets_test_threshold_group_id")
    private List<AssetsTestThreshold> assetsTestThresholds;

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

    public List<AssetsTestThreshold> getAssetsTestThresholds() {
        return assetsTestThresholds;
    }

    public void setAssetsTestThresholds(List<AssetsTestThreshold> assetsTestThresholds) {
        this.assetsTestThresholds = assetsTestThresholds;
    }

    public Map<RelationshipStatus, List<AssetsTestThreshold>> getAssetsTestThresholdsMatrix() {
        Map<RelationshipStatus, List<AssetsTestThreshold>> m = assetsTestThresholds.stream().collect(Collectors.groupingBy(AssetsTestThreshold::getRelationshipStatus));
        return m;
    }

    public AssetsTestThreshold getAssetsTestThresholdByRelationshipStatusAndHomeownerStatus(
            RelationshipStatus relationshipStatus,
            HomeownerStatus homeownerStatus) {

        return assetsTestThresholds
                .stream()
                .filter(i -> i.getRelationshipStatus().equals(relationshipStatus) && i.getHomeownerStatus().equals(homeownerStatus))
                .findFirst()
                .get();
    }
}
