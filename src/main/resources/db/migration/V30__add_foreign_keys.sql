ALTER TABLE skills
    ADD CONSTRAINT fk_skills_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

ALTER TABLE recent_searches
    ADD CONSTRAINT fk_recent_searches_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

ALTER TABLE participated_contests
    ADD CONSTRAINT fk_participated_contests_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

ALTER TABLE activities
    ADD CONSTRAINT fk_activities_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE;

ALTER TABLE attached_tags
    DROP FOREIGN KEY FK_ATTACHED_TAGS_ON_TAG;

ALTER TABLE attached_tags
    DROP FOREIGN KEY FK_ATTACHED_TAGS_ON_CONTENTS;

ALTER TABLE attached_tags
    ADD CONSTRAINT FK_ATTACHED_TAGS_ON_TAG
        FOREIGN KEY (tag_id) REFERENCES tags (id)
            ON DELETE CASCADE;

ALTER TABLE attached_tags
    ADD CONSTRAINT FK_ATTACHED_TAGS_ON_CONTENTS
        FOREIGN KEY (content_id) REFERENCES contents (id)
            ON DELETE CASCADE;