package com.tokorogadokkoi.java.realworldapp.presentation.controller.user.currentuser.response;


public record CurrentUserResponse(
        String email,
        String userName,
        String bio
) {
}
