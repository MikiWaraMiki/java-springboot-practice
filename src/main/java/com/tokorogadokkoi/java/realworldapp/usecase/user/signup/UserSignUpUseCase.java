package com.tokorogadokkoi.java.realworldapp.usecase.user.signup;

import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.User;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.UserId;
import com.tokorogadokkoi.java.realworldapp.domain.user.repository.UserRepository;
import com.tokorogadokkoi.java.realworldapp.usecase.shared.UseCase;
import com.tokorogadokkoi.java.realworldapp.usecase.shared.exception.AssertionFailException;
import com.tokorogadokkoi.java.realworldapp.usecase.shared.exception.ErrorCode;
import lombok.val;
import org.springframework.stereotype.Service;

/**
 * ユーザー会員登録ユースケースクラス
 */
@Service
public class UserSignUpUseCase implements UseCase<UserSignUpUseCaseRequest, UserSignUpResult> {
    private final UserRepository userRepository;

    public UserSignUpUseCase(
            UserRepository userRepository
    ){
        this.userRepository = userRepository;
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

        this.userRepository.create(user);

        return new UserSignUpResult(
                user.getId().getValue(),
                user.getEmailAddress().getValue()
        );
    }
}
