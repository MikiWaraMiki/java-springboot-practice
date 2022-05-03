package com.tokorogadokkoi.java.realworldapp.domain.userprofile;

import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.ErrorCode;
import lombok.NonNull;
import lombok.Value;

/**
 * ユーザー自己紹介文クラス
 */
@Value
public class UserBio {
    private static int MAX_LENGTH = 200;

    String value;

    public UserBio(@NonNull final String value) throws DomainException {
        if (value.length() > MAX_LENGTH) {
            throw new DomainException(
                    "自己紹介文が200文字を超えています",
                    ErrorCode.INVALID_VALUE_LENGTH
            );
        }

        this.value = value;
    }
}
