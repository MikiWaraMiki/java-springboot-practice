package com.tokorogadokkoi.java.realworldapp.helper.userprofile;

import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.UserId;
import com.tokorogadokkoi.java.realworldapp.domain.userprofile.model.UserBio;
import com.tokorogadokkoi.java.realworldapp.domain.userprofile.model.UserName;
import com.tokorogadokkoi.java.realworldapp.domain.userprofile.model.UserProfile;

import java.util.Optional;

/**
 * ユーザープロフィールのインスタンスを生成するテストヘルパークラス
 */
public class TestUserProfileFactory {
    public static UserProfile create(
            Optional<UserId> id,
            Optional<UserName> userName,
            Optional<UserBio> bio
    ) throws DomainException {
        return new UserProfile(
                id.orElse(UserId.create()),
                userName.orElse(new UserName("user1")),
                bio.orElse(new UserBio("biobiobio"))
        );
    }
}
