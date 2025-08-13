-- Add start_date and end_date columns to experiences table
ALTER TABLE experiences ADD COLUMN start_date VARCHAR(20);
ALTER TABLE experiences ADD COLUMN end_date VARCHAR(20);
