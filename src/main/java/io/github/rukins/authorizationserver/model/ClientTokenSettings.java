package io.github.rukins.authorizationserver.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "token_settings")
public class ClientTokenSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "access_token_ttl_seconds")
    private Long accessTokenTTLSeconds;

    @Enumerated(EnumType.STRING)
    private AccessTokenFormat accessTokenFormat;

    @Column(name = "refresh_token_ttl_seconds")
    private Long refreshTokenTTLSeconds;

    private Boolean reuseRefreshTokens;

    @OneToOne(mappedBy = "clientTokenSettings")
    private Client client;

    public ClientTokenSettings(
            Long accessTokenTTLSeconds, AccessTokenFormat accessTokenFormat,
            Long refreshTokenTTLSeconds, Boolean reuseRefreshTokens
    ) {
        this.accessTokenTTLSeconds = accessTokenTTLSeconds;
        this.accessTokenFormat = accessTokenFormat;
        this.refreshTokenTTLSeconds = refreshTokenTTLSeconds;
        this.reuseRefreshTokens = reuseRefreshTokens;
    }
}
