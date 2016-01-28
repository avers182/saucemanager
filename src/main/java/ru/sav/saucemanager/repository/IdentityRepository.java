package ru.sav.saucemanager.repository;

import ru.sav.saucemanager.domain.Identity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.annotation.Resource;
import java.util.List;

@Resource
public interface IdentityRepository extends CrudRepository<Identity, Long>{
    @Query("select ide from RepositoryEntry re join re.participantGroup pg join pg.memberships mmb join mmb.identity ide where re.id = :id order by mmb.creationDate desc")
    List<Identity> findRepositoryEntryParticipants(@Param("id") Long repositoryEntryId);
}
