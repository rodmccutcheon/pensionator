package com.rodmccutcheon.pensionator.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "age_rules")
public class AgeRule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date dateOfBirthStart;

    private Date dateOfBirthEnd;

    private double eligibleAge;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @DateTimeFormat(pattern="dd MMMM yyyy")
    public Date getDateOfBirthStart() {
        return dateOfBirthStart;
    }

    public void setDateOfBirthStart(Date dateOfBirthStart) {
        this.dateOfBirthStart = dateOfBirthStart;
    }

    @DateTimeFormat(pattern="dd MMMM yyyy")
    public Date getDateOfBirthEnd() {
        return dateOfBirthEnd;
    }

    public void setDateOfBirthEnd(Date dateOfBirthEnd) {
        this.dateOfBirthEnd = dateOfBirthEnd;
    }

    public double getEligibleAge() {
        return eligibleAge;
    }

    public void setEligibleAge(double eligibleAge) {
        this.eligibleAge = eligibleAge;
    }
}
