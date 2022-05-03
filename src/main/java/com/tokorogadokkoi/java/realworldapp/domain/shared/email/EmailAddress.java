package com.tokorogadokkoi.java.realworldapp.domain.shared.email;

import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.ErrorCode;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.Value;
import org.jooq.Domain;

/**
 * メールアドレスクラス
 * RFCには準拠せずにローカル部とドメイン部には空白以外は全て利用できるフォーマットにしている
 */
@Value
public class EmailAddress {
    private static final int MAX_LENGTH = 255;
    // NOTE: RFCに厳密に準拠しない
    private static final String EMAIL_PATTERN = "[^\s]+@[^\s]+";

    String value;

    public EmailAddress(@NonNull final String value) throws DomainException {
        if (value.isEmpty()) {
            throw new DomainException(
                    "メールアドレスが空文字です",
                    ErrorCode.INVALID_BLANK
            );
        }

        if (value.length() > MAX_LENGTH) {
            throw new DomainException(
                    "メールアドレスは255文字を超えています",
                    ErrorCode.INVALID_ULID_FORMAT
            );
        }

        if (!value.matches(EMAIL_PATTERN)) {
            throw new DomainException(
                    "メールアドレスの書式が不正です",
                    ErrorCode.INVALID_REGEX_FORMAT
            );
        }

        this.value = value;
    }
}
