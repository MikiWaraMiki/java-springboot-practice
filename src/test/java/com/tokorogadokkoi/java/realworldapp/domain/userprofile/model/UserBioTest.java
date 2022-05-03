package com.tokorogadokkoi.java.realworldapp.domain.userprofile.model;

import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.userprofile.model.UserBio;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserBioTest {
    @Nested
    @DisplayName("不変条件のテスト")
    class ConstructorTest {
        @Test
        void nullの場合はNullPointerExceptionが発生すること() {
            assertThrows(NullPointerException.class, () -> {
                new UserBio(null);
            });
        }

        @Test
        void 最大文字数を超えている場合はDomainExceptionが発生すること() {
            val exception = assertThrows(DomainException.class, () -> {
               new UserBio("a".repeat(501));
            });

            assertEquals("自己紹介文が200文字を超えています", exception.getMessage());
        }
    }
}