package io.github.rukins.authorizationserver.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientId;

    private String secret;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "clients_authentication_methods",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "authentication_method_id")
    )
    private Set<AuthenticationMethod> authenticationMethods;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "clients_grant_types",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "grant_type_id")
    )
    private Set<GrantType> grantTypes;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "clients_scopes",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "scope_id")
    )
    private Set<Scope> scopes;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER)
    private Set<RedirectUri> redirectUris;

    @OneToOne
    private ClientTokenSettings clientTokenSettings;
}
