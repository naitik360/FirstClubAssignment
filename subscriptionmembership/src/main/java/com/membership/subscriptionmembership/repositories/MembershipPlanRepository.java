package com.membership.subscriptionmembership.repositories;

import com.membership.subscriptionmembership.entities.MembershipPlan;
import com.membership.subscriptionmembership.enums.MembershipType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface MembershipPlanRepository extends JpaRepository<MembershipPlan, Long> {
    List<MembershipPlan> findAllByName(MembershipType name);
}
