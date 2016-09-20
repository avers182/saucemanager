insert into o_olatresource (resource_id, resid, resname) values
  (1, 1, 'CourseModule'),
  (2, 2, 'CourseModule'),
  (3, 3, 'Test'),
  (4, 4, 'Test');

insert into o_repositoryentry (repositoryentry_id,
                               displayname,
                               fk_olatresource,
                               fk_ownergroup,
                               fk_tutorgroup,
                               fk_participantgroup) values
  (1, 'Course A', 1, null, null, null),
  (2, 'Course B', 2, null, null, null),
  (3, 'Test A', 3, null, null, null),
  (4, 'Test B', 4, null, null, null);

insert into o_references (reference_id, source_id, target_id) values
  (1, 1, 3),
  (2, 2, 4);