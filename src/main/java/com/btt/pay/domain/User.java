package com.btt.pay.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private boolean isLoggedIn;
    private boolean isLocked;
    private int loginAttempts;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Metadata metadata;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", isLoggedIn=" + isLoggedIn +
                ", isLocked=" + isLocked +
                ", loginAttempts=" + loginAttempts +
                ", metadata=" + metadata +
                '}';
    }
}
