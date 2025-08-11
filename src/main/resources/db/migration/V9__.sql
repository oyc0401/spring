ALTER TABLE auth
    RENAME COLUMN email TO username;

ALTER TABLE auth
    DROP INDEX uc_auth_email,
    ADD CONSTRAINT uc_auth_username UNIQUE (username);
