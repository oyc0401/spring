ALTER TABLE suggested_contents 
ADD CONSTRAINT fk_suggested_contents_content_id 
FOREIGN KEY (content_id) REFERENCES contents(id) ON DELETE CASCADE;

ALTER TABLE suggested_contents 
ADD CONSTRAINT fk_suggested_contents_user_id 
FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;