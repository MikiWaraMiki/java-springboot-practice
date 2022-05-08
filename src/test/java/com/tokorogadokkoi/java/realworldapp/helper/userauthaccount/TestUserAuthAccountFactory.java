package com.tokorogadokkoi.java.realworldapp.helper.userauthaccount;

import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.UserId;
import com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.model.UserAuthAccount;
import com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.model.UserAuthAccountConnectionType;
import com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.model.UserAuthAccountId;

import java.util.Optional;

/**
 * UserAuthAccountクラスのテストインスタンス生成ファクトリ
 */
public class TestUserAuthAccountFactory {
    public static UserAuthAccount create(
            Optional<UserId> userId,
            Optional<UserAuthAccountId> userAuthAccountId
    ) throws DomainException {
        return new UserAuthAccount(
                userId.orElse(UserId.create()),
                userAuthAccountId.orElse(
                        new UserAuthAccountId(
                                UserAuthAccountConnectionType.GOOGLE,
                                "user1234"
                        )
                )
        );
    }
}
