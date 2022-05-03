package com.tokorogadokkoi.java.realworldapp.domain.user.model;

import com.github.f4b6a3.ulid.Ulid;
import com.github.f4b6a3.ulid.UlidCreator;
import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.ErrorCode;
import lombok.NonNull;
import lombok.Value;
import lombok.val;

/**
 * ユーザーIDクラス
 */
@Value
public class UserId {
    String value;

    private UserId(@NonNull final String value) throws DomainException {
        if (!Ulid.isValid(value)) {
            throw new DomainException("ユーザーIDが不正な値です", ErrorCode.INVALID_ULID_FORMAT);
        }

        this.value = value;
    }

    public static UserId create() throws DomainException {
        val value = UlidCreator.getUlid();

        return new UserId(value.toString());
    }

    static UserId reConstructor(final String value) throws DomainException {
        return new UserId(value);
    }

}
