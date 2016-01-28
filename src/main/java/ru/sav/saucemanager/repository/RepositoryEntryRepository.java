package ru.sav.saucemanager.repository;

import ru.sav.saucemanager.domain.RepositoryEntry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface RepositoryEntryRepository extends CrudRepository<RepositoryEntry, Long> {
    @Query("select re from RepositoryEntry re join re.olatResource ore where ore.resName = 'CourseModule'")
    List<RepositoryEntry> findAllCourses();

    @Query("select tgtres from References ref join ref.source src join ref.target tgt join src.repositoryEntries srcres join tgt.repositoryEntries tgtres where srcres.id = :id order by tgtres.id")
    List<RepositoryEntry> findReferences(@Param("id") Long id);
}
