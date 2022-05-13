package com.tokorogadokkoi.java.realworldapp.infra.repository.jooq;

import com.tokorogadokkoi.java.realwordapp.tables.UserProfiles;
import com.tokorogadokkoi.java.realwordapp.tables.records.UserProfilesRecord;
import com.tokorogadokkoi.java.realworldapp.domain.shared.exception.DomainException;
import com.tokorogadokkoi.java.realworldapp.domain.user.model.UserId;
import com.tokorogadokkoi.java.realworldapp.domain.userprofile.model.UserBio;
import com.tokorogadokkoi.java.realworldapp.domain.userprofile.model.UserName;
import com.tokorogadokkoi.java.realworldapp.domain.userprofile.model.UserProfile;
import com.tokorogadokkoi.java.realworldapp.domain.userprofile.repository.UserProfileRepository;
import lombok.val;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * ユーザープロフィールレポジトリjooq実装クラス
 */
@Repository
public class JooqUserProfileRepository implements UserProfileRepository {
    private final DSLContext jooq;

    public JooqUserProfileRepository(
            DSLContext jooq
    ) {
        this.jooq = jooq;
    }

    /**
     * エンティティをDBにINSERTします
     * @param userProfile UserProfileエンティティ
     */
    @Override
    public void insert(UserProfile userProfile) {
        val record = jooq.newRecord(UserProfiles.USER_PROFILES);
        record.setUserId(userProfile.userId().getValue());
        record.setUserName(userProfile.userName().getValue());
        record.setBio(userProfile.bio().getValue());

        record.insert();
    }

    /**
     * 指定したユーザーIDのプロフィール情報を取得します
     * @param userId 取得対象のユーザーID
     * @return Optional<UserProfile></UserProfile> 指定したユーザーIDのレコードが存在しない場合
     * @throws DomainException
     */
    @Override
    public Optional<UserProfile> getByUserId(UserId userId) throws DomainException {
        val result = jooq.selectFrom(UserProfiles.USER_PROFILES)
                .where(UserProfiles.USER_PROFILES.USER_ID.eq(userId.getValue()))
                .fetchOne();

        if (result == null) return Optional.empty();

        return Optional.of(this.toEntity(result));
    }

    private UserProfile toEntity(UserProfilesRecord record) throws DomainException {
        return new UserProfile(
                UserId.reConstructor(record.getUserId()),
                new UserName(record.getUserName()),
                new UserBio(record.getBio())
        );
    }
}
