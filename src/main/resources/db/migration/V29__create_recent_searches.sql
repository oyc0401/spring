CREATE TABLE recent_searches
(
    id         INT AUTO_INCREMENT NOT NULL,
    user_id    INT                NOT NULL,
    text       VARCHAR(255)       NOT NULL,
    created_at datetime           NULL,
    CONSTRAINT pk_recent_searches PRIMARY KEY (id)
);

CREATE INDEX idx_recent_search_user ON recent_searches (user_id);