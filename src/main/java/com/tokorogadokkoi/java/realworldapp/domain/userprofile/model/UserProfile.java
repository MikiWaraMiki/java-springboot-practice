package com.tokorogadokkoi.java.realworldapp.domain.userprofile.model;

import com.tokorogadokkoi.java.realworldapp.domain.user.model.UserId;

/**
 * ユーザープロフィールクラス
 */
public record UserProfile(UserId userId,
                          UserName userName,
                          UserBio bio) {
}
