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
@Table(name = "scopes")
public class Scope {
    @Id
    @Column(unique = true, updatable = false, nullable = false)
    @Enumerated(EnumType.STRING)
    private ScopeValue scopeValue;

    @ManyToMany(mappedBy = "scopes")
    private Set<Client> client;

    public Scope(ScopeValue scopeValue) {
        this.scopeValue = scopeValue;
    }
}
