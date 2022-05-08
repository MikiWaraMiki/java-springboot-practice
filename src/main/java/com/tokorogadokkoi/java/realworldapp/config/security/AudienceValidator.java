package com.tokorogadokkoi.java.realworldapp.config.security;

import lombok.val;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.Assert;

import java.util.logging.Logger;

class AudienceValidator implements OAuth2TokenValidator<Jwt> {
    private final String audience;

    AudienceValidator(String audience) {
        Assert.hasText(audience, "audience is null or empty");
        this.audience = audience;
    }

    @Override
    public OAuth2TokenValidatorResult validate(Jwt token) {
        val error = new OAuth2Error(
                "invalid_token",
                "The required audience is missing",
                null
        );

        if (token.getAudience().contains(audience)) {
            return OAuth2TokenValidatorResult.success();
        }

        return OAuth2TokenValidatorResult.failure(error);
    }
}
