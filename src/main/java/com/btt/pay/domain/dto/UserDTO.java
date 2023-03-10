package com.btt.pay.domain.dto;

import com.btt.pay.payload.request.RegisterRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements UserDetails {

    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private boolean isLoggedIn;
    private boolean isLocked;
    private int loginAttempts;
    private MetadataDTO metadata;

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public UserDTO(RegisterRequest request) {
        firstName = request.getFirstName();
        lastName = request.getLastName();
        username = request.getUsername();
        password = request.getPassword();
        email = request.getEmail();
        isLoggedIn = false;
        isLocked = false;
        loginAttempts = 0;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "read");
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", isLoggedIn=" + isLoggedIn +
                ", isLocked=" + isLocked +
                ", loginAttempts=" + loginAttempts +
                ", metadataDTO=" + metadata +
                '}';
    }
}
