package com.tokorogadokkoi.java.realworldapp.domain.user;

import com.github.f4b6a3.ulid.Ulid;
import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.ErrorCode;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserIdTest {
    @Nested
    @DisplayName("不変条件")
    class ConstructorTest {
        @Test
        void ThrowNullPointerExceptionWhenNull() {
            val exception = assertThrows(NullPointerException.class, () -> {
                UserId.reConstructor(null);
            });
        }

        @Test
        void ThrowDomainExceptionInvalidUlId() {
            val exception = assertThrows(DomainException.class, () -> {
                UserId.reConstructor("hogehoge");
            });

            assertEquals(exception.getCode(), ErrorCode.INVALID_ULID_FORMAT);
        }
    }

    @Nested
    @DisplayName("createメソッドのテスト")
    class CreateMethodTest {
        @Test
        void ユーザーIDが生成されること() throws DomainException {
            val userId = UserId.create();

            assertTrue(Ulid.isValid(userId.getValue()));
        }
    }
}