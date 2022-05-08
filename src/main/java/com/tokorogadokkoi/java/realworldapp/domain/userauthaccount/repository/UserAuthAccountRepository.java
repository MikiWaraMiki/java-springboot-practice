package com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.repository;

import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.model.UserAuthAccount;
import com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.model.UserAuthAccountId;

import java.util.Optional;

public interface UserAuthAccountRepository {
    public void insert(UserAuthAccount userAuthAccount);
    public Optional<UserAuthAccount> getByUserAuthAccountId(UserAuthAccountId userAuthAccountId) throws DomainException;
}
