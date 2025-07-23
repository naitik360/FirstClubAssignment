package com.membership.subscriptionmembership.repositories;

import com.membership.subscriptionmembership.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
