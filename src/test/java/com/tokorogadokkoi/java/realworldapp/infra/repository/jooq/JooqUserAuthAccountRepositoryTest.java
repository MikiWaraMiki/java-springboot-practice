package com.tokorogadokkoi.java.realworldapp.infra.repository.jooq;

import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.model.UserAuthAccountConnectionType;
import com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.model.UserAuthAccountId;
import com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.repository.UserAuthAccountRepository;
import com.tokorogadokkoi.java.realworldapp.helper.user.TestUserDataCreator;
import com.tokorogadokkoi.java.realworldapp.helper.userauthaccount.TestUserAuthAccountDataCreator;
import com.tokorogadokkoi.java.realworldapp.helper.userauthaccount.TestUserAuthAccountFactory;
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
class JooqUserAuthAccountRepositoryTest {
    private final JooqUserAuthAccountRepository jooqUserAuthAccountRepository;
    private final TestUserDataCreator testUserDataCreator;
    private final TestUserAuthAccountDataCreator testUserAuthAccountDataCreator;

    JooqUserAuthAccountRepositoryTest(
            @Autowired JooqUserAuthAccountRepository jooqUserAuthAccountRepository,
            @Autowired TestUserDataCreator testUserDataCreator,
            @Autowired TestUserAuthAccountDataCreator testUserAuthAccountDataCreator
    ) {
        this.jooqUserAuthAccountRepository = jooqUserAuthAccountRepository;
        this.testUserDataCreator = testUserDataCreator;
        this.testUserAuthAccountDataCreator = testUserAuthAccountDataCreator;
    }

    @Test
    @DisplayName("#create_レコードがINSERTされてIDで検索することができる")
    void WhenCreateThenCanGetByUserAuthAccountId() throws DomainException {
        val user = this.testUserDataCreator.create(
                Optional.empty(),
                Optional.empty()
        );
        val userAuthAccountId = new UserAuthAccountId(
                UserAuthAccountConnectionType.GOOGLE,
                "user1234"
        );
        val userAuthAccount = TestUserAuthAccountFactory.create(
                Optional.of(user.getId()),
                Optional.of(userAuthAccountId)
        );

        this.jooqUserAuthAccountRepository.insert(userAuthAccount);

        val result = this.jooqUserAuthAccountRepository.getByUserAuthAccountId(
                userAuthAccountId
        );

        assertTrue(result.isPresent());
        assertEquals(user.getId(), result.get().userId());
        assertEquals(userAuthAccountId, result.get().userAuthAccountId());
    }

    @Test
    @DisplayName("#getByUserAuthAccountId_指定した認証アカウントIDを持つレコードが存在しない場合はEmptyを返すこと")
    void WhenRecordNotExistReturnEmpty() throws DomainException {
        val userAuthAccountId = new UserAuthAccountId(
                UserAuthAccountConnectionType.GOOGLE,
                "user1234"
        );

        val result = this.jooqUserAuthAccountRepository.getByUserAuthAccountId(
                userAuthAccountId
        );

        assertTrue(result.isEmpty());
    }
}