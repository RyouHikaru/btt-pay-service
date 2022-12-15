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

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    private boolean isLoggedIn;

    private boolean isLocked;

    private int loginAttempts;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Metadata metadata;

}
