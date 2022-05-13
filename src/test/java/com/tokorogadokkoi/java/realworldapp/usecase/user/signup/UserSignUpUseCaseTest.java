package com.tokorogadokkoi.java.realworldapp.usecase.user.signup;

import com.tokorogadokkoi.java.realworldapp.domain.shared.email.EmailAddress;
import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.User;
import com.tokorogadokkoi.java.realworldapp.domain.user.repository.UserRepository;
import com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.model.UserAuthAccountConnectionType;
import com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.model.UserAuthAccountId;
import com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.repository.UserAuthAccountRepository;
import com.tokorogadokkoi.java.realworldapp.helper.user.TestUserFactory;
import com.tokorogadokkoi.java.realworldapp.usecase.shared.exception.AssertionFailException;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserSignUpUseCaseTest {
    private UserRepository userRepository = mock(UserRepository.class);
    private UserAuthAccountRepository userAuthAccountRepository = mock(UserAuthAccountRepository.class);
    private UserSignUpUseCase useCase = new UserSignUpUseCase(
            userRepository,
            userAuthAccountRepository
    );

    @Test
    void 同じメールアドレスを持つユーザーがすでに存在していた場合はAssertionFailExceptionが発生すること() throws DomainException {
        val email = new EmailAddress("user1@example.com");
        val userAuthAccountId = new UserAuthAccountId(
                UserAuthAccountConnectionType.GOOGLE,
                "user1234"
        );
        val userSignUpRequest = new UserSignUpUseCaseRequest(email, userAuthAccountId);

        val user = TestUserFactory.create(Optional.empty(), Optional.of(email));

        doReturn(Optional.of(user)).when(userRepository).getByEmail(email);

        val exception = assertThrows(AssertionFailException.class, () -> {
            useCase.exec(userSignUpRequest);
        });

        assertEquals("すでに登録がされています", exception.getMessage());
    }

    @Test
    void 同じメールアドレスを持つユーザーが存在しない場合はユーザーを作成し結果を返すこと() throws DomainException, AssertionFailException {
        val email = new EmailAddress("user1@example.com");
        val userAuthAccountId = new UserAuthAccountId(
                UserAuthAccountConnectionType.GOOGLE,
                "user1234"
        );
        val userSignUpRequest = new UserSignUpUseCaseRequest(email, userAuthAccountId);

        doReturn(Optional.empty()).when(userRepository).getByEmail(email);

        val result = useCase.exec(userSignUpRequest);

        assertNotNull(result.userId());
        assertEquals(email.getValue(), result.email());
    }
}