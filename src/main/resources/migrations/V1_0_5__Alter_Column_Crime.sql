ALTER TABLE dimension.crime DROP COLUMN released_to_public;

ALTER TABLE dimension.crime ADD COLUMN released_to_public TIMESTAMP WITHOUT TIME ZONE;