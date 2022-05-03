package com.tokorogadokkoi.java.realworldapp.domain.userprofile.model;

import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.ErrorCode;
import lombok.NonNull;
import lombok.Value;

/**
 * ユーザー名クラス
 */
@Value
public class UserName {
    private static int MAX_LENGTH = 15;
    private static String ALLOW_CHAR_PATTERN = "^[a-zA-Z0-9][a-zA-Z0-9_].";

    String value;

    public UserName(@NonNull final String value) throws DomainException {
        if (value.isBlank()) {
            throw new DomainException("ユーザ名が空文字です", ErrorCode.INVALID_BLANK);
        }

        if (value.length() > MAX_LENGTH) {
            throw new DomainException(
                    "ユーザ名が15文字を超えています",
                    ErrorCode.INVALID_VALUE_LENGTH
            );
        }

        if (!value.matches(ALLOW_CHAR_PATTERN)) {
            throw new DomainException(
                    "ユーザー名の書式が不正な値です",
                    ErrorCode.INVALID_REGEX_FORMAT
            );
        }

        this.value = value;
    }
}
