package io.github.rukins.authorizationserver.security.utils;

import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import io.github.rukins.authorizationserver.model.*;

import java.time.Duration;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ClientUtils {

    public static Client createClientFromRegisteredClient(RegisteredClient registeredClient) {
        Client client = new Client();

        client.setClientId(registeredClient.getClientId());
        client.setSecret(registeredClient.getClientSecret());
        client.setAuthenticationMethods(
                registeredClient.getClientAuthenticationMethods()
                        .stream().map(
                                a -> new AuthenticationMethod(
                                        AuthenticationMethodValue.valueOf(a.getValue().toUpperCase())
                                )
                        )
                        .collect(Collectors.toSet())
        );
        client.setGrantTypes(
                registeredClient.getAuthorizationGrantTypes()
                        .stream().map(
                                g -> new GrantType(
                                        GrantTypeValue.valueOf(g.getValue().toUpperCase())
                                )
                        )
                        .collect(Collectors.toSet())
        );
        client.setRedirectUris(
                registeredClient.getRedirectUris()
                        .stream().map(RedirectUri::new)
                        .collect(Collectors.toSet())
        );
        client.setScopes(
                registeredClient.getScopes()
                        .stream().map(
                                s -> new Scope(ScopeValue.valueOf(s.toUpperCase()))
                        )
                        .collect(Collectors.toSet())
        );
        client.setClientTokenSettings(
                new ClientTokenSettings(
                        registeredClient.getTokenSettings().getAccessTokenTimeToLive().getSeconds(),
                        AccessTokenFormat.valueOf(
                                registeredClient.getTokenSettings().getAccessTokenFormat().getValue()
                                        .replaceAll("-", "_")
                                        .toUpperCase()
                        ),
                        registeredClient.getTokenSettings().getRefreshTokenTimeToLive().getSeconds(),
                        registeredClient.getTokenSettings().isReuseRefreshTokens()
                )
        );

        return client;
    }

    public static RegisteredClient createRegisteredClientFromClient(Client client) {
        return RegisteredClient.withId(Long.toString(client.getId()))
                .clientId(client.getClientId())
                .clientSecret(client.getSecret())
                .clientAuthenticationMethods(clientAuthenticationMethods(client.getAuthenticationMethods()))
                .authorizationGrantTypes(authorizationGrantTypes(client.getGrantTypes()))
                .scopes(scopes(client.getScopes()))
                .redirectUris(redirectUris(client.getRedirectUris()))
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofSeconds(client.getClientTokenSettings().getAccessTokenTTLSeconds()))
                        .accessTokenFormat(new OAuth2TokenFormat(client.getClientTokenSettings().getAccessTokenFormat().getValue()))
                        .refreshTokenTimeToLive(Duration.ofSeconds(client.getClientTokenSettings().getRefreshTokenTTLSeconds()))
                        .reuseRefreshTokens(client.getClientTokenSettings().getReuseRefreshTokens())
                        .build())
                .build();
    }

    private static Consumer<Set<ClientAuthenticationMethod>> clientAuthenticationMethods(
            Set<AuthenticationMethod> authenticationMethods
    ) {
        return m -> {
            for (AuthenticationMethod a : authenticationMethods) {
                m.add(new ClientAuthenticationMethod(a.getAuthenticationMethodValue().getValue()));
            }
        };
    }

    private static Consumer<Set<AuthorizationGrantType>> authorizationGrantTypes(Set<GrantType> grantTypes) {
        return s -> {
            for (GrantType g: grantTypes) {
                s.add(new AuthorizationGrantType(g.getGrantTypeValue().getValue()));
            }
        };
    }

    private static Consumer<Set<String>> scopes(Set<Scope> scopes) {
        return set -> {
            for (Scope s : scopes) set.add(s.getScopeValue().getValue());
        };
    }

    private static Consumer<Set<String>> redirectUris(Set<RedirectUri> uris) {
        return set -> {
            for (RedirectUri u : uris) set.add(u.getUri());
        };
    }
}
