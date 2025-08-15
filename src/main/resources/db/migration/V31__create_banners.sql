CREATE TABLE banners
(
    id          INT AUTO_INCREMENT NOT NULL,
    title       VARCHAR(200)       NOT NULL,
    image_url   VARCHAR(500)       NOT NULL,
    link_url    VARCHAR(500)       NULL,
    description TEXT               NULL,
    is_active   BOOLEAN            NOT NULL DEFAULT TRUE,
    start_date  DATE               NULL,
    end_date    DATE               NULL,
    priority    INT                NOT NULL DEFAULT 0,
    created_at  datetime           NULL,
    updated_at  datetime           NULL,
    CONSTRAINT pk_banners PRIMARY KEY (id)
);

CREATE INDEX idx_banners_active_priority ON banners (is_active, priority);
CREATE INDEX idx_banners_dates ON banners (start_date, end_date);