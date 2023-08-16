package io.github.rukins.authorizationserver.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "authentication_methods")
public class AuthenticationMethod {
    @Id
    @Column(unique = true, updatable = false, nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthenticationMethodValue authenticationMethodValue;

    @ManyToMany(mappedBy = "authenticationMethods")
    private Set<Client> client;

    public AuthenticationMethod(AuthenticationMethodValue authenticationMethodValue) {
        this.authenticationMethodValue = authenticationMethodValue;
    }
}
