package com.tokorogadokkoi.java.realworldapp.presentation.controller.user.currentuser;

import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.user.repository.UserRepository;
import com.tokorogadokkoi.java.realworldapp.domain.userprofile.repository.UserProfileRepository;
import com.tokorogadokkoi.java.realworldapp.presentation.controller.user.currentuser.response.CurrentUserResponse;
import com.tokorogadokkoi.java.realworldapp.usecase.shared.exception.AssertionFailException;
import com.tokorogadokkoi.java.realworldapp.usecase.shared.exception.ErrorCode;
import com.tokorogadokkoi.java.realworldapp.usecase.shared.usersession.UserSessionProvider;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/private/user/current_user")
public class CurrentUserProfileController {
    private final UserSessionProvider userSessionProvider;
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;

    CurrentUserProfileController(
            UserSessionProvider userSessionProvider,
            UserRepository userRepository,
            UserProfileRepository userProfileRepository
    ) {
        this.userSessionProvider = userSessionProvider;
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    CurrentUserResponse index() throws DomainException, AssertionFailException {
        val userSession = this.userSessionProvider.getUserSession();
        val userId = userSession.userId();

        val user = this.userRepository
                .getById(userId)
                .orElseThrow(() -> new AssertionFailException(
                        "会員登録が完了していません",
                        ErrorCode.OPERATION_NOT_PERMITTED
                ));

        val userProfile = this.userProfileRepository.getByUserId(userId);

        if (userProfile.isEmpty())
            return new CurrentUserResponse(user.getEmailAddress().getValue(), "", "");

        return new CurrentUserResponse(
                user.getEmailAddress().getValue(),
                userProfile.get().userName().getValue(),
                userProfile.get().bio().getValue()
        );
    }
}
