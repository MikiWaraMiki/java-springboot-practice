package com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.model;

import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import lombok.val;
import org.jooq.Domain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserAuthAccountIdTest {
    @Nested
    @DisplayName("不変条件のテスト")
    class ConstructorTest {
        @Test
        @DisplayName("ID部が32文字以内の半角英小文字・数字で構成される場合はインスタンスを生成すること")
        void WhenIdLessThan32AndValidCharacterUsingReturnInstance() throws DomainException {
            val connection = UserAuthAccountConnectionType.GOOGLE;
            val id = "abcdef1234";
            val userAuthAccountId = new UserAuthAccountId(
                    connection,
                    id
            );

            assertEquals(connection, userAuthAccountId.getConnection());
            assertEquals(id, userAuthAccountId.getId());
        }

        @Test
        @DisplayName("ID部が32文字を超えている場合はDomainExceptionが発生すること")
        void WhenIdOverThan32ThrowDomainException() {
            val exception = assertThrows(DomainException.class, () -> {
                new UserAuthAccountId(
                        UserAuthAccountConnectionType.GOOGLE,
                        "a".repeat(33)
                );
            });

            assertEquals("IDの文字列長が不正な値です", exception.getMessage());
        }

        @Test
        @DisplayName("半角英小文字・数字以外が使われている場合はDomainExceptionが発生すること")
        void WhenIdUsingValidCharacterThrowDomainException() {
            val exception = assertThrows(DomainException.class, () -> {
                new UserAuthAccountId(
                        UserAuthAccountConnectionType.GOOGLE,
                        "abcdedAA"
                );
            });

            assertEquals("IDに半角英小文字・数字以外は利用できません", exception.getMessage());
        }
    }

    @Nested
    @DisplayName("fromFullIdのテスト")
    class FromFullIdTest {
        @Test
        @DisplayName("コネクション部とID部で構成されてる場合はインスタンスを返すこと")
        void WhenSeparateConnectionPartAndIdPartReturnInstance() throws DomainException {
            val fullId = "auth0|user1234";

            val userAuthAccountId = UserAuthAccountId.fromFullId(fullId);

            val expectedConnection = UserAuthAccountConnectionType.EMAIL_AND_PASSWORD;
            val expectedId = "user1234";

            assertEquals(expectedConnection, userAuthAccountId.getConnection());
            assertEquals(expectedId, userAuthAccountId.getId());
        }
        @Test
        @DisplayName("コネクション部とID部で構成されていない場合はDomainExceptionが発生すること")
        void WhenUnSeparatedConnectionPartAndIdPartThrowDomainException() {
            val exception = assertThrows(DomainException.class, () -> {
                UserAuthAccountId.fromFullId("googlehogehoge");
            });

            assertEquals("IDが不正な値です", exception.getMessage());
        }

        @Test
        @DisplayName("コネクション部が許可されていない値の場合はDomainExceptionが発生すること")
        void WhenConnectionPartInvalidConnectionThrowDomainException() {
            val exception = assertThrows(DomainException.class, () -> {
                UserAuthAccountId.fromFullId("facebook|1234");
            });
        }
    }
}