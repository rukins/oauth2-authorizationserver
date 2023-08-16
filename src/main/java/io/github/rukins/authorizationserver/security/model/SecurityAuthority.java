package io.github.rukins.authorizationserver.security.model;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import io.github.rukins.authorizationserver.model.Authority;

@AllArgsConstructor
public class SecurityAuthority implements GrantedAuthority {
    private Authority authority;

    @Override
    public String getAuthority() {
        return authority.getName();
    }
}
