-- Rename table
RENAME TABLE contests TO participated_contests;

-- Drop old index (table name required in MySQL)
DROP INDEX idx_contest_user ON participated_contests;

-- Create new index
CREATE INDEX idx_participated_contest_user ON participated_contests (user_id);
