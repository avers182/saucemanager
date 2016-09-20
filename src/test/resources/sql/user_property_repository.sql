insert into o_user (user_id) values (1), (2), (3);

insert into o_userproperty (fk_user_id, propname, propvalue) values
  (1, 'firstName', 'John'),
  (1, 'lastName', 'Doe'),
  (1, 'email', 'johndoe@email.com'),
  (2, 'firstName', 'John'),
  (2, 'lastName', 'Smith'),
  (2, 'email', 'jsmith@email.com'),
  (3, 'firstName', 'Mary'),
  (3, 'lastName', 'Smith'),
  (3, 'email', 'msmith@email.com');

insert into o_bs_identity (id, name,status, fk_user_id) values
  (1, 'John Doe', 1, 1),
  (2, 'Mr. Smith', 1, 2),
  (3, 'Mrs. Smith', 1, 3);

insert into o_repositoryentry (repositoryentry_id,
                               displayname,
                               fk_olatresource,
                               fk_ownergroup,
                               fk_tutorgroup,
                               fk_participantgroup) values
  (1, 'Course A', null, null, null, null),
  (2, 'Course B', null, null, null, null),
  (3, 'Test A', null, null, null, null),
  (4, 'Test B', null, null, null, null);

insert into o_bs_group (id, g_name) values
  (1, 'Group A'),
  (2, 'Group B');

insert into o_re_to_group (id, fk_group_id, fk_entry_id) values
  (1, 1, 1),
  (2, 1, 3),
  (3, 2, 2),
  (4, 2, 4);

insert into o_bs_group_member (id, g_role, fk_group_id, fk_identity_id) values
  (1, 'participant', 1, 1);
