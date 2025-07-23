package com.membership.subscriptionmembership.servicelayer;

import com.membership.subscriptionmembership.dto.SubscriptionRequest;
import com.membership.subscriptionmembership.entities.Benefit;
import com.membership.subscriptionmembership.entities.Membership;
import com.membership.subscriptionmembership.entities.MembershipPlan;
import com.membership.subscriptionmembership.entities.User;
import com.membership.subscriptionmembership.enums.MembershipTier;
import com.membership.subscriptionmembership.enums.MembershipType;
import com.membership.subscriptionmembership.repositories.BenefitRepository;
import com.membership.subscriptionmembership.repositories.MembershipPlanRepository;
import com.membership.subscriptionmembership.repositories.MembershipRepository;
import com.membership.subscriptionmembership.repositories.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class MembershipService {

    @Autowired private UserRepository userRepo;
    @Autowired private MembershipRepository membershipRepo;
    @Autowired private MembershipPlanRepository planRepo;
    @Autowired private BenefitRepository benefitRepo;



    public Membership subscribeUser(Long userId, SubscriptionRequest request) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getMembership() != null) {
            throw new RuntimeException("User already has a membership.");
        }

        List<MembershipPlan> plans = planRepo.findAllByName(request.getType());
        if (plans.isEmpty()) {
            throw new RuntimeException("No plan found for type: " + request.getType());
        }

        MembershipPlan plan = plans.stream()
                .min(Comparator.comparing(MembershipPlan::getPrice))
                .orElseThrow(() -> new RuntimeException("Unable to select plan"));

        Membership membership = new Membership();
        membership.setUser(user);
        membership.setType(request.getType());
        membership.setTier(request.getTier());
        membership.setStartDate(LocalDate.now());
        membership.setExpiryDate(LocalDate.now().plusMonths(getMonths(request.getType())));
        membership.setBenefits(new HashSet<>(benefitRepo.findByApplicableTier(request.getTier())));
        membership.setActive(true);

        return membershipRepo.save(membership);
    }


    public Membership upgradeTier(Long userId, MembershipTier newTier) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Membership membership = user.getMembership();
        membership.setTier(newTier);
        membership.setBenefits(new HashSet<>(benefitRepo.findByApplicableTier(newTier)));
        return membershipRepo.save(membership);
    }

    public Membership cancelSubscription(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Membership membership = user.getMembership();
        membership.setActive(false);
        return membershipRepo.save(membership);
    }

    public Membership getCurrentMembership(Long userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return user.getMembership();
    }

    public List<MembershipPlan> getAllPlans() {
        return planRepo.findAll();
    }

    public Membership getMembershipByUserId(Long userId) {
        return membershipRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Membership not found for user ID: " + userId));
    }


    private int getMonths(MembershipType type) {
        return switch (type) {
            case MONTHLY -> 1;
            case QUARTERLY -> 3;
            case YEARLY -> 12;
        };
    }
}
