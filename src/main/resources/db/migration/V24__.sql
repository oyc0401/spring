CREATE TABLE contests
(
    content_id          INT          NOT NULL,
    banner_url          VARCHAR(500) NULL,
    title               VARCHAR(200) NOT NULL,
    subtitle            VARCHAR(200) NULL,
    writer              VARCHAR(100) NULL,
    company_type        VARCHAR(100) NULL,
    target_participants VARCHAR(200) NULL,
    start_time          datetime     NULL,
    end_time            datetime     NULL,
    extra_info          TEXT         NULL,
    `description`       TEXT         NULL,
    website_url         VARCHAR(500) NULL,
    contact             VARCHAR(200) NULL,
    created_at          datetime     NULL,
    updated_at          datetime     NULL,
    CONSTRAINT pk_contests PRIMARY KEY (content_id)
);

ALTER TABLE contests
    ADD CONSTRAINT FK_CONTESTS_ON_CONTENT FOREIGN KEY (content_id) REFERENCES contents (id) ON DELETE CASCADE;