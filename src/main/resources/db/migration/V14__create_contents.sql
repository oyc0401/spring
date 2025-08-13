CREATE TABLE contents
(
    id          INT AUTO_INCREMENT NOT NULL,
    view_count  INT DEFAULT 0      NOT NULL,
    scrap_count INT DEFAULT 0      NOT NULL,
    created_at  datetime           NULL,
    CONSTRAINT pk_contents PRIMARY KEY (id)
);