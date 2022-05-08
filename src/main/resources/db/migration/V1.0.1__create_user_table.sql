CREATE TABLE users (
    id varchar(255) PRIMARY KEY,
    email varchar(255) UNIQUE NOT NULL,
    created_at timestamp  with time zone NOT NULL
);

comment on column users.id is 'ユーザーID';
comment on column users.email is 'メールアドレス';
comment on column users.created_at is 'レコード作成日時';