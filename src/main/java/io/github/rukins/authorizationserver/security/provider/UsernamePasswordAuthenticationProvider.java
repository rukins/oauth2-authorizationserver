package io.github.rukins.authorizationserver.security.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import io.github.rukins.authorizationserver.security.exception.WrongPasswordException;

@RequiredArgsConstructor
@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken usernamePasswordAuthentication = (UsernamePasswordAuthenticationToken) authentication;

        UserDetails user = userDetailsService.loadUserByUsername(usernamePasswordAuthentication.getName());

        if (!passwordEncoder.matches(
                usernamePasswordAuthentication.getCredentials().toString(),
                user.getPassword()
        )) {
            throw new WrongPasswordException("Wrong password");
        }

        UsernamePasswordAuthenticationToken authenticated = UsernamePasswordAuthenticationToken.authenticated(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities()
        );

        authenticated.setDetails(user);

        return authenticated;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
