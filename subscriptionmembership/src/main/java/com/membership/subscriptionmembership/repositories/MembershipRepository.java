package com.membership.subscriptionmembership.repositories;

import com.membership.subscriptionmembership.entities.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, Long> {
    Optional<Membership> findByUserId(Long userId);
}
