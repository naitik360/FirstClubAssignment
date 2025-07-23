package com.membership.subscriptionmembership.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    private String name;
    private String email;

    public User(Long id, String name, String email, Membership membership) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.membership = membership;
    }

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Membership membership;

}
