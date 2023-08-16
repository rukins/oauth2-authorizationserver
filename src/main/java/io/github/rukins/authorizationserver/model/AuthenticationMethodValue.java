package io.github.rukins.authorizationserver.model;

import lombok.Getter;

public enum AuthenticationMethodValue {
    CLIENT_SECRET_BASIC("client_secret_basic"),
    CLIENT_SECRET_POST("client_secret_post"),
    CLIENT_SECRET_JWT("client_secret_jwt"),
    PRIVATE_KEY_JWT("private_key_jwt");

    @Getter
    private final String value;

    AuthenticationMethodValue(String value) {
        this.value = value;
    }
}
