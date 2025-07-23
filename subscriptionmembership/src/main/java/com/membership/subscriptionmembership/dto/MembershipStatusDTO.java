package com.membership.subscriptionmembership.dto;

import com.membership.subscriptionmembership.entities.Membership;

import java.time.LocalDate;

public class MembershipStatusDTO {
    private Long id;
    private String type;

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    private String tier;
    private LocalDate startDate;
    private LocalDate expiryDate;
    private boolean active;
    private String userName;
    private String userEmail;

    public MembershipStatusDTO() {
    }

    public MembershipStatusDTO(Membership membership) {
        this.id = membership.getId();
        this.type = String.valueOf(membership.getType());
        this.tier = String.valueOf(membership.getTier());
        this.startDate = membership.getStartDate();
        this.expiryDate = membership.getExpiryDate();
        this.active = membership.isActive();
        this.userName = membership.getUser().getName();
        this.userEmail = membership.getUser().getEmail();
    }

}

