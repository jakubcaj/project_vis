------------ DIMENSION
-- CREATE SCHEMA dimension;
-- CREATE SCHEMA keystone;

CREATE SEQUENCE IF NOT EXISTS dimension.user_id_seq;
CREATE TABLE dimension.user (
  id         BIGINT PRIMARY KEY DEFAULT nextval('dimension.user_id_seq'),
  username   VARCHAR(45) NOT NULL,
  email      VARCHAR(45) NOT NULL,
  first_name VARCHAR(45) NOT NULL,
  last_name  VARCHAR(45) NOT NULL,
  full_name  VARCHAR(45)
);
------------

CREATE SEQUENCE keystone.user_auth_id_seq;

CREATE TABLE keystone.user_auth (
  id                BIGINT PRIMARY KEY   DEFAULT nextval('keystone.user_auth_id_seq'),
  dimension_user_id BIGINT      NOT NULL UNIQUE,
  username          VARCHAR(45) NOT NULL,
  password          VARCHAR(60) NOT NULL,
  enabled           BOOLEAN     NOT NULL DEFAULT TRUE,
  FOREIGN KEY (dimension_user_id) REFERENCES dimension.user (id)
);

CREATE SEQUENCE keystone.role_id_seq;

CREATE TABLE keystone.role (
  id   BIGINT PRIMARY KEY DEFAULT nextval('keystone.role_id_seq' :: REGCLASS),
  role VARCHAR(45) NOT NULL
);

ALTER TABLE keystone.role
  ADD COLUMN description VARCHAR(255);

CREATE SEQUENCE keystone.user_role_id_seq;

CREATE TABLE keystone.user_role (
  id                BIGINT PRIMARY KEY DEFAULT nextval('keystone.user_role_id_seq'),
  dimension_user_id BIGINT,
  role_id           BIGINT,
  FOREIGN KEY (dimension_user_id) REFERENCES dimension.user (id),
  FOREIGN KEY (role_id) REFERENCES keystone.role (id)
);

INSERT INTO dimension.user (username, email, first_name, last_name, full_name)
VALUES ('jcajka', 'jakubcaj@gmail.com', 'Jakub', 'Cajka', 'Jakub Cajka');

INSERT INTO keystone.user_auth (dimension_user_id, username, password) VALUES (1, 'jcajka', '$2a$10$C1yQmsNoszyN/D6pYybix.9glN2nv6j7JYxBV68l/8DywC9HWnkCq');
INSERT INTO keystone.role (role, description) VALUES ('ROLE_ADMIN', 'Can do anything');
INSERT INTO keystone.user_role (dimension_user_id, role_id) VALUES (1, 1);

INSERT into keystone.role(role, description) values('ROLE_USER', 'Standard user, can not do very much')