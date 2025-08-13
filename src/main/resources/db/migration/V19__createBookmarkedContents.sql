CREATE TABLE bookmarked_contents
(
    created_at datetime NULL,
    user_id    INT      NOT NULL,
    content_id INT      NOT NULL,
    CONSTRAINT pk_bookmarked_contents PRIMARY KEY (user_id, content_id)
);

ALTER TABLE bookmarked_contents
    ADD CONSTRAINT fk_bookmarked_contents_user
        FOREIGN KEY (user_id)
            REFERENCES users(id)
            ON DELETE CASCADE,
    ADD CONSTRAINT fk_bookmarked_contents_content
        FOREIGN KEY (content_id)
            REFERENCES contents(id)
            ON DELETE CASCADE;