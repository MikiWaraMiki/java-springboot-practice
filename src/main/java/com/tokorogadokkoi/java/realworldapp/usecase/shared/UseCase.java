package com.tokorogadokkoi.java.realworldapp.usecase.shared;

import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.usecase.shared.exception.AssertionFailException;

public interface UseCase<T, R> {
    R exec(T args) throws AssertionFailException, DomainException;
}
