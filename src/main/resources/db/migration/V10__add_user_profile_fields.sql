ALTER TABLE users
    ADD bio VARCHAR(255) NULL;

ALTER TABLE users
    ADD email VARCHAR(255) NULL;

ALTER TABLE users
    ADD grade VARCHAR(255) NULL;

ALTER TABLE users
    ADD is_grade_public BOOLEAN NOT NULL DEFAULT 0;

ALTER TABLE users
    ADD is_residence_public BOOLEAN NOT NULL DEFAULT 0;

ALTER TABLE users
    ADD residence_city VARCHAR(255) NULL;

ALTER TABLE users
    ADD residence_country VARCHAR(255) NULL;

ALTER TABLE users
    ADD school VARCHAR(255) NULL;

ALTER TABLE users
    ADD school_status VARCHAR(255) NULL;

ALTER TABLE users
    ADD vision VARCHAR(500) NULL;