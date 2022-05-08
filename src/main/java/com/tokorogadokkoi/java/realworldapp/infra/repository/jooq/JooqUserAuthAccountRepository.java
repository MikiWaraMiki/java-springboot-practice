package com.tokorogadokkoi.java.realworldapp.infra.repository.jooq;

import com.tokorogadokkoi.java.realwordapp.tables.UserAuthAccounts;
import com.tokorogadokkoi.java.realwordapp.tables.records.UserAuthAccountsRecord;
import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.UserId;
import com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.model.UserAuthAccount;
import com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.model.UserAuthAccountId;
import com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.repository.UserAuthAccountRepository;
import lombok.val;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;

@Repository
public class JooqUserAuthAccountRepository implements UserAuthAccountRepository {
    private final DSLContext jooq;

    JooqUserAuthAccountRepository(final DSLContext jooq) {
        this.jooq = jooq;
    }

    @Override
    public void insert(UserAuthAccount userAuthAccount) {
        val record = jooq.newRecord(UserAuthAccounts.USER_AUTH_ACCOUNTS);
        record.setUserId(userAuthAccount.userId().getValue());
        record.setAuthAccountId(userAuthAccount.userAuthAccountId().toString());
        record.setCreatedAt(OffsetDateTime.now());

        record.insert();
    }

    @Override
    public Optional<UserAuthAccount> getByUserAuthAccountId(UserAuthAccountId userAuthAccountId) throws DomainException {
        val result = jooq.selectFrom(UserAuthAccounts.USER_AUTH_ACCOUNTS)
                .where(
                        UserAuthAccounts.USER_AUTH_ACCOUNTS.AUTH_ACCOUNT_ID.eq(userAuthAccountId.toString())
                )
                .fetchOne();

        if (result == null) return Optional.empty();

        return Optional.of(this.toEntity(result));
    }

    private UserAuthAccount toEntity(UserAuthAccountsRecord record) throws DomainException {
        return new UserAuthAccount(
                UserId.reConstructor(record.getUserId()),
                UserAuthAccountId.fromFullId(record.getAuthAccountId())
        );
    }
}
