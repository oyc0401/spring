ALTER TABLE contents
    ADD COLUMN updated_at datetime NULL,
    ADD COLUMN is_featured BOOLEAN NOT NULL DEFAULT FALSE,
    ADD COLUMN is_active BOOLEAN NOT NULL DEFAULT TRUE,
    ADD COLUMN is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    ADD COLUMN thumbnail_url VARCHAR(500) NULL;

CREATE INDEX idx_contents_type ON contents (type);
CREATE INDEX idx_contents_active_deleted ON contents (is_active, is_deleted);
CREATE INDEX idx_contents_featured ON contents (is_featured);