package com.tokorogadokkoi.java.realworldapp.domain.shared.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_BLANK("INVALID_BLANK"),
    INVALID_ULID_FORMAT("INVALID_ULID_FORMAT"),
    INVALID_VALUE_LENGTH("INVALID_VALUE_LENGTH");

    private final String code;

    ErrorCode(final String code) {
        this.code = code;
    }
}
