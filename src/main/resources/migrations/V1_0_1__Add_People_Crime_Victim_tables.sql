CREATE SEQUENCE dimension.profile_id_seq;

CREATE TABLE dimension.profile (
  id         BIGINT PRIMARY KEY DEFAULT nextval('dimension.profile_id_seq' :: REGCLASS),
  first_name VARCHAR(45) NOT NULL,
  last_name  VARCHAR(45) NOT NULL,
  full_name  VARCHAR(45) NOT NULL,
  birth_date TIMESTAMP WITHOUT TIME ZONE,
  deceased   BOOLEAN            DEFAULT FALSE
);

CREATE SEQUENCE dimension.crime_id_seq;

CREATE TABLE dimension.crime (
  id                 BIGINT PRIMARY KEY DEFAULT nextval('dimension.crime_id_seq' :: REGCLASS),
  date_commited      TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  description        VARCHAR(2048)               NOT NULL,
  released_to_public BOOLEAN            DEFAULT FALSE
);

CREATE SEQUENCE dimension.crime_profile_id_seq;

CREATE TABLE dimension.crime_profile (
  id                   BIGINT PRIMARY KEY DEFAULT nextval('dimension.crime_profile_id_seq' :: REGCLASS),
  dimension_crime_id   BIGINT NOT NULL,
  dimension_profile_id BIGINT NOT NULL,
  FOREIGN KEY (dimension_crime_id) REFERENCES dimension.crime (id),
  FOREIGN KEY (dimension_profile_id) REFERENCES dimension.profile (id)
);

CREATE SEQUENCE dimension.victim_id_seq;
CREATE TABLE dimension.victim (
  id                   BIGINT PRIMARY KEY DEFAULT nextval('dimension.victim_id_seq' :: REGCLASS),
  dimension_profile_id BIGINT        NOT NULL,
  dimension_crime_id   BIGINT        NOT NULL,
  statement            VARCHAR(2048) NULL,
  injured              BOOLEAN            DEFAULT FALSE,
  FOREIGN KEY (dimension_profile_id) REFERENCES dimension.profile (id),
  FOREIGN KEY (dimension_crime_id) REFERENCES dimension.crime (id)
);

CREATE SEQUENCE dimension.crime_user_id_seq;
CREATE TABLE dimension.crime_user (
  id                 BIGINT PRIMARY KEY DEFAULT nextval('dimension.crime_user_id_seq' :: REGCLASS),
  dimension_crime_id BIGINT NOT NULL,
  dimension_user_id  BIGINT NOT NULL,
  FOREIGN KEY (dimension_crime_id) REFERENCES dimension.crime (id),
  FOREIGN KEY (dimension_user_id) REFERENCES dimension."user" (id)
);