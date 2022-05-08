package com.tokorogadokkoi.java.realworldapp.infra.repository.jooq;

import com.tokorogadokkoi.java.realwordapp.tables.records.UsersRecord;
import com.tokorogadokkoi.java.realworldapp.domain.shared.email.EmailAddress;
import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.User;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.UserId;
import com.tokorogadokkoi.java.realworldapp.domain.user.repository.UserRepository;
import com.tokorogadokkoi.java.realwordapp.tables.Users;
import lombok.val;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import java.time.OffsetDateTime;
import java.util.Optional;

@Repository
public class JooqUserRepository implements UserRepository {
    private final DSLContext jooq;

    public JooqUserRepository(
            DSLContext jooq
    ) {
        this.jooq = jooq;
    }

    @Override
    public void insert(User user) {
        val record = jooq.newRecord(Users.USERS);
        record.setId(user.getId().getValue());
        record.setEmail(user.getEmailAddress().getValue());
        record.setCreatedAt(OffsetDateTime.now());

        record.insert();
    }

    @Override
    public Optional<User> getById(UserId userId) throws DomainException {
        val result = jooq.selectFrom(Users.USERS)
                .where(Users.USERS.ID.eq(userId.getValue()))
                .fetchOne();

        if (result == null) return Optional.empty();

        return Optional.of(this.toEntity(result));
    }

    @Override
    public Optional<User> getByEmail(EmailAddress email) throws DomainException {
        val result = jooq.selectFrom(Users.USERS)
                .where(Users.USERS.EMAIL.eq(email.getValue()))
                .fetchOne();

        if (result == null) return Optional.empty();

        return Optional.of(this.toEntity(result));
    }

    private User toEntity(final UsersRecord record) throws DomainException {
        return User.reConstructor(
                UserId.reConstructor(record.getId()),
                new EmailAddress(record.getEmail())
        );
    }
}
