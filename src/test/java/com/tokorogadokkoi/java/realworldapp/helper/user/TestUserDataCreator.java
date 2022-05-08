package com.tokorogadokkoi.java.realworldapp.helper.user;

import com.tokorogadokkoi.java.realworldapp.domain.shared.email.EmailAddress;
import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.User;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.UserId;
import com.tokorogadokkoi.java.realworldapp.domain.user.repository.UserRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.Optional;

@Component
public class TestUserDataCreator {
    private final UserRepository userRepository;

    public TestUserDataCreator(
            @Autowired UserRepository userRepository
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
