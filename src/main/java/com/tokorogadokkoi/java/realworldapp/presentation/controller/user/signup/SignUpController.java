package com.tokorogadokkoi.java.realworldapp.presentation.controller.user.signup;

import com.auth0.exception.Auth0Exception;
import com.tokorogadokkoi.java.realworldapp.domain.shared.email.EmailAddress;
import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.model.UserAuthAccountId;
import com.tokorogadokkoi.java.realworldapp.presentation.controller.user.signup.response.SignUpResponse;
import com.tokorogadokkoi.java.realworldapp.usecase.shared.auth.AuthApiService;
import com.tokorogadokkoi.java.realworldapp.usecase.shared.exception.AssertionFailException;
import com.tokorogadokkoi.java.realworldapp.usecase.user.signup.UserSignUpUseCase;
import com.tokorogadokkoi.java.realworldapp.usecase.user.signup.UserSignUpUseCaseRequest;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/private/user/signup")
@CrossOrigin("*")
public class SignUpController {
    private final AuthApiService authApiService;
    private final UserSignUpUseCase userSignUpUseCase;

    SignUpController(
            AuthApiService authApiService,
            UserSignUpUseCase userSignUpUseCase
    ) {
        this.authApiService = authApiService;
        this.userSignUpUseCase = userSignUpUseCase;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void test() {
        val authentication = SecurityContextHolder.getContext().getAuthentication();
        final Jwt jwt = (Jwt) authentication.getPrincipal();
        System.out.println(jwt.getTokenValue());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    SignUpResponse create() throws DomainException, AssertionFailException, Auth0Exception {
        val authentication = SecurityContextHolder.getContext().getAuthentication();
        final Jwt jwt = (Jwt) authentication.getPrincipal();

        val emailAddress = this.authApiService.getEmailFromToken(
                jwt.getTokenValue()
        );
        val userAuthAccountId = UserAuthAccountId.fromFullId(
                jwt.getSubject()
        );

        val request = new UserSignUpUseCaseRequest(emailAddress, userAuthAccountId);
        val result = this.userSignUpUseCase.exec(request);

        return new SignUpResponse(result.userId());
    }
}
