package com.tokorogadokkoi.java.realworldapp.usecase.user.signup;

import com.tokorogadokkoi.java.realworldapp.domain.shared.email.EmailAddress;
import com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.model.UserAuthAccountId;

/**
 * 会員登録ユースケース実行リクエスト
 */
public record UserSignUpUseCaseRequest(EmailAddress email, UserAuthAccountId userAuthAccountId) {
}
