package com.tokorogadokkoi.java.realworldapp.helper.userprofile;

import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.UserId;
import com.tokorogadokkoi.java.realworldapp.domain.userprofile.model.UserBio;
import com.tokorogadokkoi.java.realworldapp.domain.userprofile.model.UserName;
import com.tokorogadokkoi.java.realworldapp.domain.userprofile.model.UserProfile;
import com.tokorogadokkoi.java.realworldapp.domain.userprofile.repository.UserProfileRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * UserProfileを生成しDBに永続化を行うテストヘルパークラス
 */
@Component
public class TestUserProfileDataCreator {
    private final UserProfileRepository userProfileRepository;

    TestUserProfileDataCreator(
            @Autowired final UserProfileRepository userProfileRepository
    ) {
        this.userProfileRepository = userProfileRepository;
    }

    public UserProfile create(
            Optional<UserId> id,
            Optional<UserName> userName,
            Optional<UserBio> bio
    ) throws DomainException {
        val userProfile = TestUserProfileFactory.create(id, userName, bio);

        this.userProfileRepository.insert(userProfile);

        return userProfile;
    }
}
