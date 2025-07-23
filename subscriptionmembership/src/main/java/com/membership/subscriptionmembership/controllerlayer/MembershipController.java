package com.membership.subscriptionmembership.controllerlayer;

import com.membership.subscriptionmembership.dto.MembershipStatusDTO;
import com.membership.subscriptionmembership.dto.SubscriptionRequest;
import com.membership.subscriptionmembership.entities.Membership;
import com.membership.subscriptionmembership.entities.MembershipPlan;
import com.membership.subscriptionmembership.enums.MembershipTier;
import com.membership.subscriptionmembership.servicelayer.MembershipService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/membership")
@Tag(name = "Membership API")
public class MembershipController {

    @Autowired
    private MembershipService membershipService;

    @PostMapping("/subscribe/{userId}")
    public ResponseEntity<Membership> subscribe(@PathVariable Long userId, @RequestBody SubscriptionRequest request) {
        return ResponseEntity.ok(membershipService.subscribeUser(userId, request));
    }


    @PostMapping("/upgrade/{userId}/{tier}")
    public ResponseEntity<Membership> upgrade(@PathVariable Long userId, @PathVariable MembershipTier tier) {
        return ResponseEntity.ok(membershipService.upgradeTier(userId, tier));
    }

    @PostMapping("/cancel/{userId}")
    public ResponseEntity<Membership> cancel(@PathVariable Long userId) {
        return ResponseEntity.ok(membershipService.cancelSubscription(userId));
    }

    @GetMapping("/status/{userId}")
    public ResponseEntity<Membership> getMembershipByUserId(@PathVariable Long userId) {
        Membership membership = membershipService.getMembershipByUserId(userId);
        return ResponseEntity.ok(membership);
    }


    @GetMapping("/plans")
    public ResponseEntity<List<MembershipPlan>> getPlans() {
        return ResponseEntity.ok(membershipService.getAllPlans());
    }
}

