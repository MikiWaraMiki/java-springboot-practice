package com.tokorogadokkoi.java.realworldapp.domain.shared.exception;

import lombok.Getter;
import lombok.NonNull;

/**
 * ドメイン層の例外クラス
 */
@Getter
public class DomainException extends Exception {
    private final ErrorCode code;
    public DomainException(@NonNull final String errorMessage, @NonNull final ErrorCode code) {
        super(errorMessage);
        this.code = code;
    }
}
