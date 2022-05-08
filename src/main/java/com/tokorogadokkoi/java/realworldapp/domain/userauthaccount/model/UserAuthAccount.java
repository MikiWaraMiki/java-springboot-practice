package com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.model;

import com.tokorogadokkoi.java.realworldapp.domain.user.model.UserId;
import lombok.Getter;
import lombok.NonNull;

@Getter
public record UserAuthAccount(UserId userId,
                              UserAuthAccountId userAuthAccountId) {
    public UserAuthAccount(@NonNull final UserId userId, @NonNull UserAuthAccountId userAuthAccountId) {
        this.userId = userId;
        this.userAuthAccountId = userAuthAccountId;
    }
}
