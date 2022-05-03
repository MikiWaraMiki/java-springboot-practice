package com.tokorogadokkoi.java.realworldapp.domain.user;

import com.tokorogadokkoi.java.realworldapp.domain.shared.email.EmailAddress;
import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Nested
    @DisplayName("createメソッドのテスト")
    class CreateTest {
        @Test
        void 指定したメールアドレスを持つ利用者が作成されること() throws DomainException {
            val email = new EmailAddress("user1@example.com");
            val user = User.create(email);

            assertNotNull(user.getId());
            assertEquals(email, user.getEmailAddress());
        }
    }

    @Nested
    @DisplayName("reConstructorメソッドのテスト")
    class ReConstructorTest {
        @Test
        void 指定したIDとメールアドレスを持つ利用者が作成されること() throws DomainException {
            val userId = UserId.create();
            val email = new EmailAddress("user1@exmaple.com");

            val user = User.reConstructor(userId, email);

            assertEquals(userId, user.getId());
            assertEquals(email, user.getEmailAddress());
        }
    }

    @Nested
    @DisplayName("同一性テスト")
    class EqualsTest {
        @Test
        void IDが同じ利用者は同一と判定されること() throws DomainException {
            val userId = UserId.create();
            val user = User.reConstructor(
                    userId,
                    new EmailAddress("user1@example.com")
            );

            val sameUserIdUser = User.reConstructor(
                    userId,
                    new EmailAddress("user2@example.com")
            );

            assertEquals(user, sameUserIdUser);
        }
    }
}