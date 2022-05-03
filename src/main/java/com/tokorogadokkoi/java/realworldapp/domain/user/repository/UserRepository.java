package com.tokorogadokkoi.java.realworldapp.domain.user.repository;

import com.tokorogadokkoi.java.realworldapp.domain.shared.email.EmailAddress;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.User;

import java.util.Optional;

/**
 * ユーザーリポジトリインターフェース
 */
public interface UserRepository {
    void create(User user);
    Optional<User> getByEmail(EmailAddress email);
}
