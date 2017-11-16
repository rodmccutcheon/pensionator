package com.rodmccutcheon.pensionator.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 2, max = 30)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 30)
    private String lastName;

    @NotNull
    private Date dateOfBirth;

    @NotNull
    private String gender;

    @OneToOne
    @JoinColumn(name = "homeowner_status_id")
    @NotNull
    private HomeownerStatus homeownerStatus;

    @OneToOne
    @JoinColumn(name = "relationship_status_id")
    @NotNull
    private RelationshipStatus relationshipStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "partner_id")
    private Client partner;

    @OneToMany(fetch = FetchType.EAGER,
            orphanRemoval = true,
            cascade = { CascadeType.REMOVE, CascadeType.DETACH, CascadeType.MERGE },
            mappedBy = "client")
    @OrderBy("date DESC")
    private Set<Calculation> calculations;

    public Client() { }

    public Client(String firstName,
                  String lastName,
                  Date dateOfBirth,
                  String gender,
                  HomeownerStatus homeownerStatus,
                  RelationshipStatus relationshipStatus) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.homeownerStatus = homeownerStatus;
        this.relationshipStatus = relationshipStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @DateTimeFormat(pattern="dd MMMM yyyy")
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        LocalDate dob = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(dob, LocalDate.now()).getYears();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public HomeownerStatus getHomeownerStatus() {
        return homeownerStatus;
    }

    public void setHomeownerStatus(HomeownerStatus homeownerStatus) {
        this.homeownerStatus = homeownerStatus;
    }

    public RelationshipStatus getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(RelationshipStatus relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public Client getPartner() {
        return partner;
    }

    public void setPartner(Client partner) {
        this.partner = partner;
    }

    public Set<Calculation> getCalculations() {
        return calculations;
    }

    public void setCalculations(Set<Calculation> calculations) {
        this.calculations = calculations;
    }
}
