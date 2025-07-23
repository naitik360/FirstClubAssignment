package com.membership.subscriptionmembership.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.membership.subscriptionmembership.enums.MembershipTier;
import com.membership.subscriptionmembership.enums.MembershipType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MembershipType type;

    @Enumerated(EnumType.STRING)
    private MembershipTier tier;

    public Membership(Long id, MembershipType type, MembershipTier tier, LocalDate startDate, LocalDate expiryDate, boolean active, User user, Set<Benefit> benefits) {
        this.id = id;
        this.type = type;
        this.tier = tier;
        this.startDate = startDate;
        this.expiryDate = expiryDate;
        this.active = active;
        this.user = user;
        this.benefits = benefits;
    }

    private LocalDate startDate;

    public Membership() {
    }

    private LocalDate expiryDate;
    private boolean active = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MembershipType getType() {
        return type;
    }

    public void setType(MembershipType type) {
        this.type = type;
    }

    public MembershipTier getTier() {
        return tier;
    }

    public void setTier(MembershipTier tier) {
        this.tier = tier;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Benefit> getBenefits() {
        return benefits;
    }

    public void setBenefits(Set<Benefit> benefits) {
        this.benefits = benefits;
    }

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "membership_benefits",
            joinColumns = @JoinColumn(name = "membership_id"),
            inverseJoinColumns = @JoinColumn(name = "benefit_id"))
    private Set<Benefit> benefits;
}
