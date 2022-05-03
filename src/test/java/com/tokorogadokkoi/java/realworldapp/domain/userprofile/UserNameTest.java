package com.tokorogadokkoi.java.realworldapp.domain.userprofile;

import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class UserNameTest {
    @Nested
    @DisplayName("不変条件のテスト")
    class ConstructorTest {
        @Test
        void 先頭が半角英数字で始まり2文字目以降は半角英数字とアンダースコアが構成される15文字以内の文字列の場合はオブジェクトが生成される() throws DomainException {
            val value = "a".repeat(5) + "A".repeat(5) + "1".repeat(3) + "_".repeat(2);
            val userName = new UserName(value);

            assertEquals(value, userName.getValue());
        }
        @Test
        void nullの場合はNullPointerExceptionが発生すること() {
            assertThrows(NullPointerException.class, () -> {
                new UserName(null);
            });
        }

        @ParameterizedTest
        @ValueSource(strings = {"", " "})
        void 文字が入力されていない場合はDomainExceptionが発生すること(String value) {
            val exception = assertThrows(DomainException.class, () -> {
                new UserName(value);
            });

            assertEquals("ユーザ名が空文字です", exception.getMessage());
        }

        @Test
        void 最大文字数を超えている場合はDomainExceptionが発生すること() {
            val exception = assertThrows(DomainException.class, () -> {
                new UserName("a".repeat(16));
            });

            assertEquals("ユーザ名が50文字を超えています", exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {
                " a",
                "_hogehoge",
                "あ1235",
                "１２３４"
        })
        void 先頭に半角英数字以外が利用されている場合はDomainExceptionが発生すること(String value) {
            val exception = assertThrows(DomainException.class, () -> {
                new UserName(value);
            });

            assertEquals("ユーザー名の書式が不正な値です", exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "a ",
                "a@",
                "aあ",
                "a！"
        })
        void 半角英数字とアンダースコア以外が含まれている場合はDomainExceptionが発生すること(String value) {
            val exception = assertThrows(DomainException.class, () -> {
                new UserName(value);
            });

            assertEquals("ユーザー名の書式が不正な値です", exception.getMessage());
        }
    }
}