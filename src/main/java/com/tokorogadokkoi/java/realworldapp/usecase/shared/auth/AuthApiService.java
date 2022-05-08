package com.tokorogadokkoi.java.realworldapp.usecase.shared.auth;

import com.auth0.exception.Auth0Exception;
import com.tokorogadokkoi.java.realworldapp.domain.shared.email.EmailAddress;
import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import org.springframework.security.oauth2.jwt.Jwt;

public interface AuthApiService {
    public EmailAddress getEmailFromToken(String jwtTokenValue) throws Auth0Exception, DomainException;
}
