package com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.model;

import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.ErrorCode;
import lombok.NonNull;
import lombok.Value;
import lombok.val;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * ユーザー認証アカウントID
 * 外部IDaaS(Auth0)で発行されたIDを保持する
 */
@Value
public class UserAuthAccountId {
    private static final String CONNECTION_AND_ID_SEPARATE_CHAR = "[|]";
    // NOTE: [コネクション名]|[ID部]
    private static final int SEPARATE_RESULT_LENGTH = 2;
    private static final int ID_MAX_LENGTH = 32;
    private static final String ID_PATTERN = "^[0-9a-zA-Z]+$";

    UserAuthAccountConnectionType connection;
    String id;

    public UserAuthAccountId(@NonNull UserAuthAccountConnectionType connection, @NonNull String id) throws DomainException {
        if (id.length() > ID_MAX_LENGTH) {
            throw new DomainException("IDの文字列長が不正な値です", ErrorCode.INVALID_VALUE_LENGTH);
        }

        if (!id.matches(ID_PATTERN)) {
            throw new DomainException("IDに半角英小文字・数字以外は利用できません", ErrorCode.INVALID_REGEX_FORMAT);
        }

        this.id = id;
        this.connection = connection;
    }

    @Override
    public String toString() {
        return connection.getConnectionName() + "|" + id;
    }

    public static UserAuthAccountId fromFullId(@NonNull final String fullId) throws DomainException {
        val splitSeparatorResult = fullId.split(CONNECTION_AND_ID_SEPARATE_CHAR);

        if (splitSeparatorResult.length != SEPARATE_RESULT_LENGTH) {
            throw new DomainException("IDが不正な値です", ErrorCode.INVALID_REGEX_FORMAT);
        }

        val connection = UserAuthAccountConnectionType.getFromConnectionName(
                splitSeparatorResult[0]
        );

        return new UserAuthAccountId(connection, splitSeparatorResult[1]);
    }
}
