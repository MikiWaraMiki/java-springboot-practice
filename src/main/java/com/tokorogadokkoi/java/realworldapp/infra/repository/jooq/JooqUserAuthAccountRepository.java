package com.tokorogadokkoi.java.realworldapp.infra.repository.jooq;

import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.UserId;
import com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.model.UserAuthAccount;
import com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.model.UserAuthAccountId;
import com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.repository.UserAuthAccountRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JooqUserAuthAccountRepository implements UserAuthAccountRepository {

    @Override
    public Optional<UserAuthAccount> getByUserAuthAccountId(UserAuthAccountId userAuthAccountId) throws DomainException {
        return Optional.of(new UserAuthAccount(UserId.create(), userAuthAccountId));
    }
}
