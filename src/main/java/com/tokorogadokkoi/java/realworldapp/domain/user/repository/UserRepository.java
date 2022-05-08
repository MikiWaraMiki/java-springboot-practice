package com.tokorogadokkoi.java.realworldapp.domain.user.repository;

import com.tokorogadokkoi.java.realworldapp.domain.shared.email.EmailAddress;
import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.User;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.UserId;

import java.util.Optional;

/**
 * ユーザーリポジトリインターフェース
 */
public interface UserRepository {
    void insert(User user);
    Optional<User> getById(UserId userId) throws DomainException;
    Optional<User> getByEmail(EmailAddress email) throws DomainException;
}
