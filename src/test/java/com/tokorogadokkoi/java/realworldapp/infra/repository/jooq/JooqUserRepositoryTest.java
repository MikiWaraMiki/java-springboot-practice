package com.tokorogadokkoi.java.realworldapp.infra.repository.jooq;

import com.tokorogadokkoi.java.realworldapp.domain.shared.email.EmailAddress;
import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.UserId;
import com.tokorogadokkoi.java.realworldapp.domain.user.repository.UserRepository;
import com.tokorogadokkoi.java.realworldapp.helper.user.TestUserDataCreator;
import com.tokorogadokkoi.java.realworldapp.helper.user.TestUserFactory;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class JooqUserRepositoryTest{
    private final UserRepository userRepository;
    private final TestUserDataCreator testUserDataCreator;

    JooqUserRepositoryTest(@Autowired JooqUserRepository userRepository) {
        this.userRepository = userRepository;
        this.testUserDataCreator = new TestUserDataCreator(
                this.userRepository
        );
    }

    @Test
    @DisplayName("#insert_INSERTが実行されてidで検索ができること")
    void InsertSuccessAndCanFindById() throws DomainException {
        val user = TestUserFactory.create(
                Optional.empty(),
                Optional.empty()
        );

        this.userRepository.insert(user);

        val result = this.userRepository.getById(user.getId());

        assertTrue(result.isPresent());
        assertEquals(user.getId(), result.get().getId());
        assertEquals(user.getEmailAddress(), result.get().getEmailAddress());
    }

    @Test
    @DisplayName("getById_指定したIDのレコードが存在しない場合はemptyを返すこと")
    void GetByIdWhenRecordIsNotExistReturnOptionalEmpty() throws DomainException {
        val userId = UserId.create();

        val result = this.userRepository.getById(userId);

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("getByEmail_指定したメールアドレスのレコードが存在する場合はUserを返すこと")
    void GetByEmailWhenRecordExistReturnUser() throws DomainException {
        val email = new EmailAddress("user1@example.com");
        val user = this.testUserDataCreator.create(
                Optional.empty(),
                Optional.of(email)
        );

        val result = this.userRepository.getByEmail(email);

        assertTrue(result.isPresent());
        assertEquals(user.getId(), result.get().getId());
        assertEquals(email, result.get().getEmailAddress());
    }

    @Test
    @DisplayName("getByEmail_指定したメールアドレスのレコードが存在しない場合はEmptyを返すこと")
    void GetByEmailWhenRecordNotExistReturnOptionalEmpty() throws DomainException {
        val email = new EmailAddress("user1@example.com");

        val result = this.userRepository.getByEmail(email);

        assertTrue(result.isEmpty());
    }
}