package com.tokorogadokkoi.java.realworldapp.helper.userauthaccount;

import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.UserId;
import com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.model.UserAuthAccount;
import com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.model.UserAuthAccountId;
import com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.repository.UserAuthAccountRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * UserAuthAccountインスタンスを生成しDBにINSERTするテストヘルパークラス
 */
@Component
public class TestUserAuthAccountDataCreator {
    private final UserAuthAccountRepository userAuthAccountRepository;

    public TestUserAuthAccountDataCreator(
            @Autowired  final UserAuthAccountRepository userAuthAccountRepository
    ) {
        this.userAuthAccountRepository = userAuthAccountRepository;
    }

    public UserAuthAccount create(
            Optional<UserId> userId,
            Optional<UserAuthAccountId> userAuthAccountId
    ) throws DomainException {
        val userAuthAccount = TestUserAuthAccountFactory.create(userId, userAuthAccountId);

        this.userAuthAccountRepository.insert(userAuthAccount);

        return userAuthAccount;
    }
}
