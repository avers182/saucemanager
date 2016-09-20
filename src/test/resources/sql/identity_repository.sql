insert into o_bs_identity (id, name,status, fk_user_id) values
  (1, 'John Doe', 1, null),
  (2, 'Mr. Smith', 1, null),
  (3, 'Mrs. Smith', 1, null);

insert into o_bs_secgroup (id) values (1), (2), (3), (4), (5), (6), (7), (8), (9), (10), (11), (12);

insert into o_repositoryentry (repositoryentry_id,
                               displayname,
                               fk_olatresource,
                               fk_ownergroup,
                               fk_tutorgroup,
                               fk_participantgroup) values
  (1, 'Course A', null, 1, 2, 3),
  (2, 'Course B', null, 4, 5, 6),
  (3, 'Course C', null, 7, 8, 9),
  (4, 'Course D', null, 10, 11, 12);

insert into o_bs_membership (id, identity_id, secgroup_id) values
  (1, 1, 1),
  (2, 1, 4),
  (3, 1, 7),
  (4, 1, 12),
  (5, 2, 3),
  (6, 2, 6),
  (7, 2, 9),
  (8, 2, 12),
  (9, 3, 10),
  (10, 3, 3),
  (11, 3, 6);



