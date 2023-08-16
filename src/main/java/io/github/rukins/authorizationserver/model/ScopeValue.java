package io.github.rukins.authorizationserver.model;

import lombok.Getter;

public enum ScopeValue {
    OPENID("openid");

    @Getter
    private final String value;

    ScopeValue(String value) {
        this.value = value;
    }
}
