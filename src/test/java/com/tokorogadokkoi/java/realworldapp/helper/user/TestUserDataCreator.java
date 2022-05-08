package com.tokorogadokkoi.java.realworldapp.helper.user;

import com.tokorogadokkoi.java.realworldapp.domain.shared.email.EmailAddress;
import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.User;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.UserId;
import com.tokorogadokkoi.java.realworldapp.domain.user.repository.UserRepository;
import lombok.val;

import javax.swing.text.html.Option;
import java.util.Optional;

public class TestUserDataCreator {
    private final UserRepository userRepository;

    public TestUserDataCreator(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    public User create(
            Optional<UserId> id,
            Optional<EmailAddress> email
    ) throws DomainException {
        val user = TestUserFactory.create(id, email);

        this.userRepository.insert(user);

        return user;
    }
}
