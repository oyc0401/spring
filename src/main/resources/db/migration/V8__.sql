CREATE TABLE auth
(
    user_id        INT          NOT NULL,
    email          VARCHAR(255) NULL,
    password       VARCHAR(255) NULL,
    oauth_id       VARCHAR(255) NULL,
    login_provider VARCHAR(255) NOT NULL,
    user_role      VARCHAR(255) NOT NULL,
    CONSTRAINT pk_auth PRIMARY KEY (user_id),
    CONSTRAINT fk_auth_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

ALTER TABLE auth
    ADD CONSTRAINT uc_auth_email UNIQUE (email);

ALTER TABLE auth
    ADD CONSTRAINT uc_auth_oauth UNIQUE (oauth_id);