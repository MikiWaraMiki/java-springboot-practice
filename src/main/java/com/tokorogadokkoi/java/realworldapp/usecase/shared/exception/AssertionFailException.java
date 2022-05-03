package com.tokorogadokkoi.java.realworldapp.usecase.shared.exception;

import lombok.Getter;

/**
 * ユースケースの事前条件違反例外
 */
@Getter
public class AssertionFailException  extends  Exception {
    private final ErrorCode code;

    public AssertionFailException(String msg, ErrorCode code) {
        super(msg);
        this.code = code;
    }
}
