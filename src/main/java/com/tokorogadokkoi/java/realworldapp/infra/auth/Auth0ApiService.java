package com.tokorogadokkoi.java.realworldapp.infra.auth;

import com.auth0.client.auth.AuthAPI;
import com.auth0.client.mgmt.ManagementAPI;
import com.auth0.exception.Auth0Exception;
import com.tokorogadokkoi.java.realworldapp.domain.shared.email.EmailAddress;
import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.model.UserAuthAccountId;
import com.tokorogadokkoi.java.realworldapp.usecase.shared.auth.AuthApiService;
import lombok.NonNull;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class Auth0ApiService implements AuthApiService {
    private final AuthAPI api;

    Auth0ApiService(
            @Value("auth0.domain") final String domain,
            @Value("auth0.clientId")  final String clientId,
            @Value("auth0.clientSecret")  final String clientSecret
    ) {
        this.api = new AuthAPI(
            domain,
            clientId,
            clientSecret
        );
    }

    /**
     * JWTトークンの内容からUserのメールアドレスを取得する
     * @param jwtTokenValue
     * @return ユーザーのメールアドレス
     * @throws Auth0Exception
     */
    @Override
    public EmailAddress getEmailFromToken(@NonNull final String jwtTokenValue) throws Auth0Exception, DomainException {
        val request = this.api.userInfo(jwtTokenValue);

        val userInfo = request.execute();

        System.out.println(userInfo.getValues().toString());
        return new EmailAddress(
                (String) userInfo.getValues().get("email")
        );
    }
}
