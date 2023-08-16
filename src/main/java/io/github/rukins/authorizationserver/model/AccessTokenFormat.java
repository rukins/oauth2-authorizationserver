package io.github.rukins.authorizationserver.model;

import lombok.Getter;

public enum AccessTokenFormat {
    SELF_CONTAINED("self-contained"),
    REFERENCE("reference");

    @Getter
    private final String value;

    AccessTokenFormat(String value) {
        this.value = value;
    }
}
