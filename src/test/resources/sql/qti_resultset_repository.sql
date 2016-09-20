insert into o_olatresource (resource_id, resid, resname) values
  (1, 1, 'Course'),
  (2, 2, 'Course'),
  (3, 3, 'Course'),
  (4, 4, 'Course');

insert into o_repositoryentry (repositoryentry_id,
                               displayname,
                               fk_olatresource,
                               fk_ownergroup,
                               fk_tutorgroup,
                               fk_participantgroup) values
  (1, 'Course A', 1, null, null, null),
  (2, 'Course B', 2, null, null, null),
  (3, 'Course C', 3, null, null, null),
  (4, 'Course D', 4, null, null, null);

insert into o_qtiresultset (resultset_id,
                            identity_id,
                            olatresource_fk,
                            ispassed,
                            score,
                            duration,
                            issuspended) values
  (1, null, 1, true, 5, 100, false),
  (2, null, 1, true, 5, 100, false),
  (3, null, 1, true, 5, 100, false),
  (4, null, 3, true, 5, 100, false),
  (5, null, 3, false, 2, 100, false),
  (6, null, 4, true, 5, 100, false),
  (7, null, 4, true, 4, 100, false),
  (8, null, 4, true, 4, 100, false),
  (9, null, 4, true, 3, 100, false);
