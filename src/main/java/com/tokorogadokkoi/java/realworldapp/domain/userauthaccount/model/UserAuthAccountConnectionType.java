package com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.model;

import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.ErrorCode;
import lombok.Getter;
import lombok.val;

import java.util.Arrays;

/**
 * 認証アカウントのコネクション種別
 */
@Getter
public enum UserAuthAccountConnectionType {
    GOOGLE("google"),
    EMAIL_AND_PASSWORD("auth0");

    private final String connectionName;

    UserAuthAccountConnectionType(final String connectionName) {
        this.connectionName = connectionName;
    }

    static UserAuthAccountConnectionType getFromConnectionName(final String targetConnectionName) throws DomainException {
        return Arrays.stream(values()).filter(
                data -> data.connectionName.equals(targetConnectionName)
        ).findFirst().orElseThrow(
                () -> new DomainException("許可されていない認証アカウントのコネクションです", ErrorCode.INVALID_VALUE_LENGTH)
        );
    }
}
