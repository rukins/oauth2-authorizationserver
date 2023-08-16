package io.github.rukins.authorizationserver.security.exception;

import org.springframework.security.core.AuthenticationException;

public class WrongPasswordException extends AuthenticationException {
    public WrongPasswordException(String msg) {
        super(msg);
    }

    public WrongPasswordException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
