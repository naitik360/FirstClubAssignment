package com.membership.subscriptionmembership.entities;

import com.membership.subscriptionmembership.enums.MembershipTier;
import jakarta.persistence.*;

@Entity
public class Benefit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private MembershipTier applicableTier;

    public Benefit(Long id, String name, String description, MembershipTier applicableTier) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.applicableTier = applicableTier;
    }


    public Benefit() {
    }
    public MembershipTier getApplicableTier() {
        return applicableTier;
    }

    public void setApplicableTier(MembershipTier applicableTier) {
        this.applicableTier = applicableTier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
