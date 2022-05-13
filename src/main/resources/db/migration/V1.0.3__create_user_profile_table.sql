CREATE TABLE user_profiles (
    user_id varchar(26) PRIMARY KEY,
    user_name varchar(255) UNIQUE NOT NULL,
    bio text,
    CONSTRAINT fk_user_profiles_user_id
                           FOREIGN KEY(user_id)
                           REFERENCES users(id)
);

comment on column user_profiles.user_id is 'ユーザーID';
comment on column user_profiles.user_name is 'ユーザー名';
comment on column user_profiles.bio is '自己紹介文';