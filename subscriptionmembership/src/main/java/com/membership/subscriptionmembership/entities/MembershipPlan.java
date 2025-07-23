package com.membership.subscriptionmembership.entities;

import com.membership.subscriptionmembership.enums.MembershipType;
import jakarta.persistence.*;

@Entity
public class MembershipPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MembershipType name;

    public MembershipPlan() {
    }

    public MembershipPlan(Long id, MembershipType name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MembershipType getName() {
        return name;
    }

    public void setName(MembershipType name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private double price;
}
