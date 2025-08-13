ALTER TABLE contents
    ADD banner_url VARCHAR(500) NULL;

ALTER TABLE contents
    ADD company_type VARCHAR(100) NULL;

ALTER TABLE contents
    ADD end_time VARCHAR(255) NULL;

ALTER TABLE contents
    ADD start_time VARCHAR(255) NULL;

ALTER TABLE contents
    ADD title VARCHAR(255) NULL;

ALTER TABLE contents
    ADD writer VARCHAR(100) NULL;

ALTER TABLE contents
    MODIFY banner_url VARCHAR(500) NOT NULL;

ALTER TABLE contents
    MODIFY company_type VARCHAR(100) NOT NULL;

ALTER TABLE contents
    MODIFY end_time VARCHAR(255) NOT NULL;

ALTER TABLE contents
    MODIFY start_time VARCHAR(255) NOT NULL;

ALTER TABLE contents
    MODIFY title VARCHAR(255) NOT NULL;

ALTER TABLE contents
    MODIFY writer VARCHAR(100) NOT NULL;

ALTER TABLE contents
    MODIFY type VARCHAR(100) NOT NULL;