package com.membership.subscriptionmembership.repositories;

import com.membership.subscriptionmembership.entities.Benefit;
import com.membership.subscriptionmembership.enums.MembershipTier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BenefitRepository extends JpaRepository<Benefit, Long> {
    List<Benefit> findByApplicableTier(MembershipTier tier);
}
