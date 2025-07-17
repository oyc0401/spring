ALTER TABLE users
    ADD email VARCHAR(255) NULL;

ALTER TABLE users
    ADD oauth_id VARCHAR(255) NULL;

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);

ALTER TABLE users
    ADD CONSTRAINT uc_users_oauth UNIQUE (oauth_id);

ALTER TABLE users
    DROP COLUMN username;