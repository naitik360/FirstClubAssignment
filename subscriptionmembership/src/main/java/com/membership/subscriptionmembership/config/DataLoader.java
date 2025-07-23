package com.membership.subscriptionmembership.config;

import com.membership.subscriptionmembership.entities.*;
import com.membership.subscriptionmembership.enums.MembershipTier;
import com.membership.subscriptionmembership.enums.MembershipType;
import com.membership.subscriptionmembership.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(
            UserRepository userRepository,
            MembershipRepository membershipRepository,
            BenefitRepository benefitRepository,
            MembershipPlanRepository membershipPlanRepository
    ) {
        return args -> {
            // ✅ Create Benefits
            Benefit b1 = new Benefit(null, "Priority Support", "Get priority access to support", MembershipTier.GOLD);
            Benefit b2 = new Benefit(null, "Free Delivery", "No delivery charges", MembershipTier.SILVER);
            Benefit b3 = new Benefit(null, "Exclusive Deals", "Access to premium deals", MembershipTier.PLATINUM);
            Benefit b4 = new Benefit(null, "Lounge Access", "Access to premium lounges", MembershipTier.PLATINUM);
            Benefit b5 = new Benefit(null, "Extended Warranty", "Warranty on selected items", MembershipTier.GOLD);

            benefitRepository.saveAll(List.of(b1, b2, b3, b4, b5));

            // ✅ Create Membership Plans
            MembershipPlan monthly = new MembershipPlan(null, MembershipType.MONTHLY, 199.99);
            MembershipPlan quarterly = new MembershipPlan(null, MembershipType.QUARTERLY, 499.99);
            MembershipPlan yearly = new MembershipPlan(null, MembershipType.YEARLY, 1499.99);

            membershipPlanRepository.saveAll(List.of(monthly, quarterly, yearly));

            // ✅ Create Users
            User user1 = new User(null, "John Doe", "john@example.com", null);
            User user2 = new User(null, "Jane Smith", "jane@example.com", null);
            User user3 = new User(null, "Alex Brown", "alex@example.com", null);

            userRepository.saveAll(List.of(user1, user2, user3));

            // ✅ Create Memberships
            Membership m1 = new Membership(null, MembershipType.MONTHLY, MembershipTier.GOLD,
                    LocalDate.now(), LocalDate.now().plusMonths(1), true, user1, Set.of(b1, b2, b5));

            Membership m2 = new Membership(null, MembershipType.QUARTERLY, MembershipTier.PLATINUM,
                    LocalDate.now(), LocalDate.now().plusMonths(3), true, user2, Set.of(b1, b2, b3, b4));

            Membership m3 = new Membership(null, MembershipType.YEARLY, MembershipTier.SILVER,
                    LocalDate.now(), LocalDate.now().plusYears(1), true, user3, Set.of(b2));

            membershipRepository.saveAll(List.of(m1, m2, m3));

            // ✅ Link memberships back to users
            user1.setMembership(m1);
            user2.setMembership(m2);
            user3.setMembership(m3);

            userRepository.saveAll(List.of(user1, user2, user3));
        };
    }
}
