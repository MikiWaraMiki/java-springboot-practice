package com.tokorogadokkoi.java.realworldapp.helper.user;

import com.tokorogadokkoi.java.realworldapp.domain.shared.email.EmailAddress;
import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.User;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.UserId;
import lombok.val;

import java.util.Optional;

/**
 * Userクラスのテストインスタンス生成ファクトリ
 */
public class TestUserFactory {
    public static User create(Optional<UserId> id, Optional<EmailAddress> email) throws DomainException {
        return User.reConstructor(
                id.orElse(UserId.create()),
                email.orElse(new EmailAddress("user1@example.com"))
        );
    }
}
