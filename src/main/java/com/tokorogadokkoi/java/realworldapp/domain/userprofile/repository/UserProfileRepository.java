package com.tokorogadokkoi.java.realworldapp.domain.userprofile.repository;

import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.UserId;
import com.tokorogadokkoi.java.realworldapp.domain.userprofile.model.UserProfile;

import java.util.Optional;

/**
 * ユーザープロフィールレポジトリ
 */
public interface UserProfileRepository {
    void insert(UserProfile userProfile);
    Optional<UserProfile> getByUserId(UserId userId) throws DomainException;
}
