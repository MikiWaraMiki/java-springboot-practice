package com.tokorogadokkoi.java.realworldapp.domain.userauthaccount.model;

import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserAuthAccountConnectionTypeTest {
    @Nested
    @DisplayName("getFromConnectionNameのテスト")
    class GetFromConnectionNameTest {
        @Test
        @DisplayName("コネクション名が許可された値の場合は種別を返す")
        void WhenValidConnectionNameReturnUserAuthAccountConnectionType() throws DomainException {
            val connectionName = "google";

            val result = UserAuthAccountConnectionType.getFromConnectionName(
                    connectionName
            );

            assertEquals(connectionName, result.getConnectionName());
        }

        @Test
        @DisplayName("コネクション名が許可されていない場合はDomainExceptionが発生すること")
        void WhenInValidConnectionNameThrowDomainException() {
            val exception = assertThrows(DomainException.class, () -> {
                UserAuthAccountConnectionType.getFromConnectionName("facebook");
            });

            assertEquals("許可されていない認証アカウントのコネクションです", exception.getMessage());
        }
    }
}