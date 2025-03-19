package com.gdms.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for Member functionality.
 */
class MemberTest {
    private Member member;
    private final LocalDate startDate = LocalDate.now();
    private final LocalDate endDate = startDate.plusMonths(1);

    @BeforeEach
    void setUp() {
        member = new Member(
            1,
            "John Doe",
            "john@example.com",
            "password123",
            "Premium",
            startDate,
            endDate
        );
    }

    @Test
    void testMemberCreation() {
        assertNotNull(member);
        assertEquals(1, member.getUserId());
        assertEquals("John Doe", member.getName());
        assertEquals("john@example.com", member.getEmail());
        assertEquals("Premium", member.getMembershipType());
        assertEquals(startDate, member.getMembershipStartDate());
        assertEquals(endDate, member.getMembershipEndDate());
        assertTrue(member.isActive());
    }

    @Test
    void testAuthentication() {
        assertTrue(member.authenticate("password123"));
        assertFalse(member.authenticate("wrongpassword"));
    }

    @Test
    void testMembershipActive() {
        assertTrue(member.isMembershipActive());
        
        // Test with expired membership
        Member expiredMember = new Member(
            2,
            "Jane Doe",
            "jane@example.com",
            "password456",
            "Basic",
            LocalDate.now().minusMonths(2),
            LocalDate.now().minusMonths(1)
        );
        assertFalse(expiredMember.isMembershipActive());
    }

    @Test
    void testRenewMembership() {
        // Test renewal of active membership
        member.renewMembership(3);
        assertEquals(endDate, member.getMembershipStartDate());
        assertEquals(endDate.plusMonths(3), member.getMembershipEndDate());

        // Test renewal of expired membership
        Member expiredMember = new Member(
            2,
            "Jane Doe",
            "jane@example.com",
            "password456",
            "Basic",
            LocalDate.now().minusMonths(2),
            LocalDate.now().minusMonths(1)
        );
        expiredMember.renewMembership(3);
        assertEquals(LocalDate.now(), expiredMember.getMembershipStartDate());
        assertEquals(LocalDate.now().plusMonths(3), expiredMember.getMembershipEndDate());
    }

    @Test
    void testGetRole() {
        assertEquals("MEMBER", member.getRole());
    }

    @Test
    void testToString() {
        String memberString = member.toString();
        assertTrue(memberString.contains("userId=1"));
        assertTrue(memberString.contains("name='John Doe'"));
        assertTrue(memberString.contains("email='john@example.com'"));
        assertTrue(memberString.contains("membershipType='Premium'"));
    }

    @Test
    void testEqualsAndHashCode() {
        Member sameMember = new Member(
            1,
            "John Doe",
            "john@example.com",
            "password123",
            "Premium",
            startDate,
            endDate
        );
        Member differentMember = new Member(
            2,
            "Jane Doe",
            "jane@example.com",
            "password456",
            "Basic",
            startDate,
            endDate
        );

        assertEquals(member, sameMember);
        assertNotEquals(member, differentMember);
        assertEquals(member.hashCode(), sameMember.hashCode());
        assertNotEquals(member.hashCode(), differentMember.hashCode());
    }
} 