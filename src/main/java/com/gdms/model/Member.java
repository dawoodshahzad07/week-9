package com.gdms.model;

import java.time.LocalDate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Represents a gym member in the system.
 * Extends the User class and adds member-specific attributes and functionality.
 */
public class Member extends User {
    private String membershipType;
    private LocalDate membershipStartDate;
    private LocalDate membershipEndDate;
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Constructor for creating a new member.
     * @param userId Unique identifier for the member
     * @param name Full name of the member
     * @param email Email address
     * @param password Password (will be encrypted)
     * @param membershipType Type of membership (e.g., "Basic", "Premium")
     * @param membershipStartDate Start date of membership
     * @param membershipEndDate End date of membership
     */
    public Member(int userId, String name, String email, String password,
                 String membershipType, LocalDate membershipStartDate, LocalDate membershipEndDate) {
        super(userId, name, email, passwordEncoder.encode(password));
        this.membershipType = membershipType;
        this.membershipStartDate = membershipStartDate;
        this.membershipEndDate = membershipEndDate;
    }

    // Getters and Setters
    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public LocalDate getMembershipStartDate() {
        return membershipStartDate;
    }

    public void setMembershipStartDate(LocalDate membershipStartDate) {
        this.membershipStartDate = membershipStartDate;
    }

    public LocalDate getMembershipEndDate() {
        return membershipEndDate;
    }

    public void setMembershipEndDate(LocalDate membershipEndDate) {
        this.membershipEndDate = membershipEndDate;
    }

    /**
     * Checks if the membership is currently active.
     * @return true if membership is active and not expired, false otherwise
     */
    public boolean isMembershipActive() {
        return isActive() && LocalDate.now().isBefore(membershipEndDate);
    }

    /**
     * Renews the membership for a specified number of months.
     * @param months Number of months to extend the membership
     */
    public void renewMembership(int months) {
        if (membershipEndDate.isBefore(LocalDate.now())) {
            membershipStartDate = LocalDate.now();
        } else {
            membershipStartDate = membershipEndDate;
        }
        membershipEndDate = membershipStartDate.plusMonths(months);
    }

    @Override
    public boolean authenticate(String password) {
        return passwordEncoder.matches(password, getPassword());
    }

    @Override
    public String getRole() {
        return "MEMBER";
    }

    @Override
    public String toString() {
        return "Member{" +
                "userId=" + getUserId() +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", membershipType='" + membershipType + '\'' +
                ", membershipStartDate=" + membershipStartDate +
                ", membershipEndDate=" + membershipEndDate +
                ", isActive=" + isActive() +
                '}';
    }
} 