CREATE TABLE experiences
(
    id         INT AUTO_INCREMENT NOT NULL,
    user_id    INT                NOT NULL,
    title      VARCHAR(120)       NOT NULL,
    details    LONGTEXT           NULL,
    priority   INT DEFAULT 0      NOT NULL,
    created_at datetime           NULL,
    CONSTRAINT pk_experiences PRIMARY KEY (id)
);

CREATE INDEX idx_experience_user ON experiences (user_id);