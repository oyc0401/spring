CREATE TABLE activities
(
    id         INT AUTO_INCREMENT NOT NULL,
    user_id    INT                NOT NULL,
    title      VARCHAR(120)       NOT NULL,
    details    LONGTEXT           NULL,
    priority   INT DEFAULT 0      NOT NULL,
    created_at datetime           NULL,
    CONSTRAINT pk_activities PRIMARY KEY (id)
);

CREATE INDEX idx_activity_user ON activities (user_id);