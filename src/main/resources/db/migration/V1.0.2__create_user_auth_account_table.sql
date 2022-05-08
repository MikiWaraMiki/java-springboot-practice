CREATE TABLE user_auth_accounts (
    user_id varchar(255) PRIMARY KEY,
    auth_account_id varchar(255) UNIQUE NOT NULL,
    created_at timestamp  with time zone NOT NULL,
    CONSTRAINT fk_user_auth_accounts_user_id
                                FOREIGN KEY(user_id)
                                REFERENCES users(id)
);

comment on column user_auth_accounts.user_id is 'ユーザーID';
comment on column user_auth_accounts.auth_account_id is 'Auth0上のユーザーID';
comment on column user_auth_accounts.created_at is 'レコード作成日時';