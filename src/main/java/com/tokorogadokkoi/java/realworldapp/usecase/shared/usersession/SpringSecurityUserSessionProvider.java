package com.tokorogadokkoi.java.realworldapp.usecase.shared.usersession;

import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.UserId;
import com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.model.UserAuthAccountId;
import com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.repository.UserAuthAccountRepository;
import com.tokorogadokkoi.java.realworldapp.usecase.shared.exception.AssertionFailException;
import com.tokorogadokkoi.java.realworldapp.usecase.shared.exception.ErrorCode;
import lombok.val;
import org.jooq.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class SpringSecurityUserSessionProvider implements UserSessionProvider {
    private final UserAuthAccountRepository userAuthAccountRepository;

    SpringSecurityUserSessionProvider(
            UserAuthAccountRepository userAuthAccountRepository
    ) {
        this.userAuthAccountRepository = userAuthAccountRepository;
    }

    @Override
    public UserSession getUserSession() throws DomainException, AssertionFailException {
        val authentication = SecurityContextHolder.getContext().getAuthentication();

        val jwt = (Jwt) authentication.getPrincipal();

        val userAuthAccountId = UserAuthAccountId.fromFullId(jwt.getSubject());

        val userAuthAccount = this.userAuthAccountRepository.getByUserAuthAccountId(
                userAuthAccountId
        ).orElseThrow(() -> new AssertionFailException("ログインしてください", ErrorCode.OPERATION_NOT_PERMITTED));

        return new UserSession(userAuthAccount.getUserId());
    }
}
