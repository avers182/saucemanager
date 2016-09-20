insert into o_olatresource (resource_id, resid, resname) values
  (1, 1, 'CourseModule'),
  (2, 2, 'CourseModule'),
  (3, 3, 'NotCourseModule'),
  (4, 4, 'NotCourseModule');

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
