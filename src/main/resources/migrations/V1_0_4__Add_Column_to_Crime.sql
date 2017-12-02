ALTER TABLE dimension.crime ADD COLUMN short_description VARCHAR(60);
ALTER TABLE dimension.crime RENAME COLUMN date_commited TO date_committed;
ALTER TABLE dimension.crime_profile RENAME TO suspect;