package io.github.rukins.authorizationserver.model;

import lombok.Getter;

public enum GrantTypeValue {
    AUTHORIZATION_CODE("authorization_code"),
    REFRESH_TOKEN("refresh_token"),
    CLIENT_CREDENTIALS("client_credentials");

    @Getter
    private final String value;

    GrantTypeValue(String value) {
        this.value = value;
    }
}
