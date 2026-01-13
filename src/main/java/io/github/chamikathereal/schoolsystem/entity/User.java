package io.github.chamikathereal.schoolsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data                   // Lombok: Generates Getters, Setters, toString
@Builder                // Lombok: Helps create objects cleanly
@NoArgsConstructor      // Lombok: Empty constructor (Required by JPA)
@AllArgsConstructor     // Lombok: Full constructor
@Entity                 // JPA: Marks this class as a Database Table
@Table(name = "users")  // JPA: Names the table 'users'
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email; // We will use email as the 'username' for login

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING) // Saves role as text ("TEACHER") instead of numbers (0,1,2)
    private Role role;

    // --- UserDetails Implementation (For Spring Security Phase) ---
    // These methods allow Spring Security to understand our custom User class

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Converts our "Role" enum into a permission Spring understands
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        // We use email to login
        return email;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}