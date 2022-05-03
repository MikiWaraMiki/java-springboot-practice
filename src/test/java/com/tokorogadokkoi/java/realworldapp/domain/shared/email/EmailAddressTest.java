package com.tokorogadokkoi.java.realworldapp.domain.shared.email;

import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import lombok.val;
import org.jooq.Domain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmailAddressTest {
    @Nested
    @DisplayName("不変条件テスト")
    class ConstructorTest {
        @Test
        void nullの場合はNullPointerExceptionが発生すること() {
            assertThrows(NullPointerException.class, () -> {
                new EmailAddress(null);
            });
        }

        @Test
        void 空文字の場合はDomainExceptionが発生すること() {
            val exception = assertThrows(DomainException.class, () -> {
                new EmailAddress("");
            });

            assertEquals("メールアドレスが空文字です", exception.getMessage());
        }

        @Test
        void 最大文字数を超えている場合はDomainExceptionが発生すること() {
            val validDomain = "@example.com";
            val repeatCount = 255 - validDomain.length() + 1;
            val exception = assertThrows(DomainException.class, () -> {
                new EmailAddress("a".repeat(repeatCount) + validDomain);
            });

            assertEquals("メールアドレスは255文字を超えています", exception.getMessage());
        }

        @Test
        void メールアドレスにアットマークが含まれていない場合はDomainExceptionが発生すること() {
            val exception = assertThrows(DomainException.class, () -> {
                new EmailAddress("hogehoge.exmaple.com");
            });

            assertEquals("メールアドレスの書式が不正です", exception.getMessage());
        }

        @Test
        void ローカル部に空白が利用されている場合はDomainExceptionが発生すること() {
            val exception = assertThrows(DomainException.class, () -> {
                new EmailAddress("user 1@exmaple.com");
            });

            assertEquals("メールアドレスの書式が不正です", exception.getMessage());
        }

        @Test
        void ドメイン部に空白が利用されている場合はDomainExceptionが発生すること() {
            val exception = assertThrows(DomainException.class, () -> {
                new EmailAddress("user1@ex ampple.com");
            });

            assertEquals("メールアドレスの書式が不正です", exception.getMessage());
        }
    }
}