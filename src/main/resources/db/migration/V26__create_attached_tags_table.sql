CREATE TABLE attached_tags
(
    name       VARCHAR(255) NOT NULL,
    content_id BIGINT       NOT NULL,
    tag_id     BIGINT       NOT NULL,
    CONSTRAINT pk_attached_tags PRIMARY KEY (content_id, tag_id)
);

ALTER TABLE attached_tags
    ADD CONSTRAINT FK_ATTACHED_TAGS_ON_TAG FOREIGN KEY (tag_id) REFERENCES tags (id);

ALTER TABLE attached_tags
    ADD CONSTRAINT FK_ATTACHED_TAGS_ON_CONTENTS FOREIGN KEY (content_id) REFERENCES contents (id);