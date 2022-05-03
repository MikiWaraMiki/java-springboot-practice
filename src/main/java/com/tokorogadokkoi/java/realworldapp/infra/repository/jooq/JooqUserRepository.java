package com.tokorogadokkoi.java.realworldapp.infra.repository.jooq;

import com.tokorogadokkoi.java.realworldapp.domain.shared.email.EmailAddress;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.User;
import com.tokorogadokkoi.java.realworldapp.domain.user.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JooqUserRepository implements UserRepository {
    @Override
    public void create(User user) {

    }

    @Override
    public Optional<User> getByEmail(EmailAddress email) {
        return Optional.empty();
    }
}
