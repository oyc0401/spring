CREATE TABLE recommends
(
    user_id    INT              NOT NULL,
    content_id INT              NOT NULL,
    view       BIT(1) DEFAULT 0 NOT NULL,
    created_at datetime         NULL,
    CONSTRAINT pk_recommends PRIMARY KEY (user_id, content_id)
);

ALTER TABLE recommends
    ADD CONSTRAINT fk_recommends_user
        FOREIGN KEY (user_id)
            REFERENCES users(id)
            ON DELETE CASCADE,
    ADD CONSTRAINT fk_recommends_content
        FOREIGN KEY (content_id)
            REFERENCES contents(id)
            ON DELETE CASCADE;