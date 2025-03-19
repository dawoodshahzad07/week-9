package com.gdms.model;

import java.util.Objects;

/**
 * Abstract base class for all users in the system.
 * Implements common attributes and methods shared by Admin, Trainer, and Member classes.
 */
public abstract class User {
    private int userId;
    private String name;
    private String email;
    private String password;
    private boolean isActive;

    /**
     * Constructor for creating a new user.
     * @param userId Unique identifier for the user
     * @param name Full name of the user
     * @param email Email address
     * @param password Encrypted password
     */
    protected User(int userId, String name, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.isActive = true;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    protected String getPassword() {
        return password;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * Abstract method for user authentication.
     * @param password The password to verify
     * @return true if authentication successful, false otherwise
     */
    public abstract boolean authenticate(String password);

    /**
     * Abstract method to get the user's role.
     * @return String representing the user's role
     */
    public abstract String getRole();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return userId == user.userId && 
               Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", isActive=" + isActive +
                '}';
    }
} 