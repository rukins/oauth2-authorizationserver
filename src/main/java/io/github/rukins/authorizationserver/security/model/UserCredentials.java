package io.github.rukins.authorizationserver.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserCredentials {
    private String username;
    private String password;
}
