package com.tokorogadokkoi.java.realworldapp.usecase.shared.usersession;

import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.usecase.shared.exception.AssertionFailException;

interface UserSessionProvider {
    UserSession getUserSession() throws DomainException, AssertionFailException;
}
