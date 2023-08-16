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
@Table(name = "grant_types")
public class GrantType {
    @Id
    @Column(unique = true, updatable = false, nullable = false)
    @Enumerated(EnumType.STRING)
    private GrantTypeValue grantTypeValue;

    @ManyToMany(mappedBy = "grantTypes")
    private Set<Client> client;

    public GrantType(GrantTypeValue grantTypeValue) {
        this.grantTypeValue = grantTypeValue;
    }
}
