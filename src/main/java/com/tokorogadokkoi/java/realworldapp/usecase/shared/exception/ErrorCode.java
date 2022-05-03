package com.tokorogadokkoi.java.realworldapp.usecase.shared.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    DATA_ALREADY_EXIST("DATA_ALREADY_EXIST"),
    OPERATION_NOT_PERMITTED("OPERATION_NOT_PERMITTED"),
    DATA_NOT_FOUND("DATA_NOT_FOUND");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }
}
