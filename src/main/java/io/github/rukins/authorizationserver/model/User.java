package io.github.rukins.authorizationserver.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Entity
@Table(name = "users")
public class User {
    @Id
    private String email;

    @Column(unique = true, nullable = false)
    private String username;

    private String password;

    @Column(name = "utc_created_at", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "utc_updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_authorities",
            joinColumns = @JoinColumn(name = "user_email"),
            inverseJoinColumns = @JoinColumn(name = "authority_name")
    )
    private Set<Authority> authorities;
}
