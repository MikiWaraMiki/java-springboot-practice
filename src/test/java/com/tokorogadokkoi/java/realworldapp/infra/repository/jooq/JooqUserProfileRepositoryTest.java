package com.tokorogadokkoi.java.realworldapp.infra.repository.jooq;

import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.UserId;
import com.tokorogadokkoi.java.realworldapp.domain.userprofile.model.UserBio;
import com.tokorogadokkoi.java.realworldapp.domain.userprofile.model.UserName;
import com.tokorogadokkoi.java.realworldapp.domain.userprofile.repository.UserProfileRepository;
import com.tokorogadokkoi.java.realworldapp.helper.user.TestUserDataCreator;
import com.tokorogadokkoi.java.realworldapp.helper.userprofile.TestUserProfileDataCreator;
import com.tokorogadokkoi.java.realworldapp.helper.userprofile.TestUserProfileFactory;
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
class JooqUserProfileRepositoryTest {
    private final UserProfileRepository userProfileRepository;
    private final TestUserDataCreator testUserDataCreator;
    private final TestUserProfileDataCreator testUserProfileDataCreator;

    JooqUserProfileRepositoryTest(
            @Autowired UserProfileRepository userProfileRepository,
            @Autowired TestUserDataCreator testUserDataCreator,
            @Autowired TestUserProfileDataCreator testUserProfileDataCreator
    ) {
        this.userProfileRepository = userProfileRepository;
        this.testUserDataCreator = testUserDataCreator;
        this.testUserProfileDataCreator = testUserProfileDataCreator;
    }

    @Test
    @DisplayName("#create_レコードがINSERTされてユーザーIDで検索ができること")
    void WhenCreateThenCanGetByUserAuthAccountId() throws DomainException {
        val user = this.testUserDataCreator.create(
                Optional.empty(),
                Optional.empty()
        );

        val userProfile = TestUserProfileFactory.create(
                Optional.of(user.getId()),
                Optional.of(new UserName("user1")),
                Optional.of(new UserBio("user1 bio"))
        );

        this.userProfileRepository.insert(userProfile);

        val result = this.userProfileRepository.getByUserId(user.getId());

        assertTrue(result.isPresent());
        assertEquals(userProfile.userId(), result.get().userId());
        assertEquals(userProfile.userName(), result.get().userName());
        assertEquals(userProfile.bio(), result.get().bio());
    }

    @Test
    @DisplayName("#getByUserId_指定した利用者IDのレコードが存在しない場合はemptyを返す")
    void GetByUserIdWhenRecordIsNotExistReturnOptionalEmpty() throws DomainException {
        val userId = UserId.create();

        val result = this.userProfileRepository.getByUserId(userId);

        assertTrue(result.isEmpty());
    }
}