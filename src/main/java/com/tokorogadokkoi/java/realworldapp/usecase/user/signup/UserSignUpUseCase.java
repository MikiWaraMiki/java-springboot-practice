package com.tokorogadokkoi.java.realworldapp.usecase.user.signup;

import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.User;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.UserId;
import com.tokorogadokkoi.java.realworldapp.domain.user.repository.UserRepository;
import com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.model.UserAuthAccount;
import com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.repository.UserAuthAccountRepository;
import com.tokorogadokkoi.java.realworldapp.usecase.shared.UseCase;
import com.tokorogadokkoi.java.realworldapp.usecase.shared.exception.AssertionFailException;
import com.tokorogadokkoi.java.realworldapp.usecase.shared.exception.ErrorCode;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ユーザー会員登録ユースケースクラス
 */
@Service
@Transactional
public class UserSignUpUseCase implements UseCase<UserSignUpUseCaseRequest, UserSignUpResult> {
    private final UserRepository userRepository;
    private final UserAuthAccountRepository userAuthAccountRepository;

    public UserSignUpUseCase(
            UserRepository userRepository,
            UserAuthAccountRepository userAuthAccountRepository
    ){
        this.userRepository = userRepository;
        this.userAuthAccountRepository = userAuthAccountRepository;
    }

    /**
     * 利用者の会員登録処理を実行します
     * @param args 会員登録ユースケースのリクエスト
     * @return 利用者ID
     * @throws com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException ドメインルール違反
     * @throws com.tokorogadokkoi.java.realworldapp.usecase.shared.exception.AssertionFailException すでに同じメールアドレスを持つ利用者が存在する場合
     */
    @Override
    public UserSignUpResult exec(UserSignUpUseCaseRequest args) throws AssertionFailException, DomainException {
        val result = this.userRepository.getByEmail(args.email());

        if (result.isPresent()) {
            throw new AssertionFailException(
                    "すでに登録がされています",
                    ErrorCode.DATA_ALREADY_EXIST
            );
        }

        val user = User.create(args.email());

        this.userRepository.insert(user);

        val userAuthAccount = new UserAuthAccount(
                user.getId(),
                args.userAuthAccountId()
        );
        this.userAuthAccountRepository.insert(userAuthAccount);

        return new UserSignUpResult(
                user.getId().getValue(),
                user.getEmailAddress().getValue()
        );
    }
}
