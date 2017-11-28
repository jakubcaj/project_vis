-- INSERT INTO keystone.role (role, description) VALUES ('ROLE_PROFILES', 'Can see, add, delete, edit people profiles');
-- INSERT INTO keystone.role (role, description) VALUES ('ROLE_RELEASE_PUBLIC', 'Can release stuff to public');

alter table dimension.profile ADD COLUMN email VARCHAR(45);
alter table dimension.profile add COLUMN phone VARCHAR(20);
