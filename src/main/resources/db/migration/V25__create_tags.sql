CREATE TABLE tags
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    tag_name VARCHAR(255)          NOT NULL,
    CONSTRAINT pk_tags PRIMARY KEY (id)
);

ALTER TABLE tags
    ADD CONSTRAINT uc_tags_tag_name UNIQUE (tag_name);