package ru.sav.saucemanager.repository;

import ru.sav.saucemanager.domain.UserProperty;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Resource
public interface UserPropertyRepository extends CrudRepository<UserProperty, Long>{
    @Query("select distinct ide.id, ide.name, usp1.propValue, usp2.propValue, usp3.propValue, min(mmbs.creationDate) " +
            "from RepositoryEntry re " +
            "join re.repositoryEntryToBsGroups rebsg " +
            "join rebsg.bsGroup bsg " +
            "join bsg.groupMembers mmbs " +
            "join mmbs.identity ide " +
            "join ide.user use " +
            "left join use.userProperties usp1 on usp1.propName = 'firstName' " +
            "left join use.userProperties usp2 on usp2.propName = 'lastName' " +
            "left join use.userProperties usp3 on usp3.propName = 'email' " +
            "where re.id = :repositoryEntryId " +
            "group by ide.id, ide.name, usp1.propValue, usp2.propValue, usp3.propValue " +
            "order by min(mmbs.creationDate) desc"
    )
    List<Object[]> findByRepositoryEntryId(@Param("repositoryEntryId") Long repositoryEntryId);

    @Query(value = "select i.id, i.name, up1.propvalue as v1, up2.propvalue as v2, up3.propvalue as v3, s.creationdate" +
            " from (" +
            "  select distinct gm.fk_identity_id as identity_id, min(gm.creationdate) as creationdate" +
            "   from o_repositoryentry re" +
            "    inner join o_re_to_group rtg on rtg.fk_entry_id = re.repositoryentry_id" +
            "    inner join o_bs_group bsg on bsg.id = rtg.fk_group_id" +
            "    inner join o_bs_group_member gm on gm.fk_group_id = bsg.id" +
            "   where re.repositoryentry_id = :repositoryEntryId" +
            "   group by gm.fk_identity_id" +
            " ) s" +
            "  inner join o_bs_identity i on i.id = s.identity_id" +
            "  inner join o_user u on u.user_id = i.fk_user_id" +
            "  left join o_userproperty up1 on up1.fk_user_id = u.user_id and up1.propname = 'firstName'" +
            "  left join o_userproperty up2 on up2.fk_user_id = u.user_id and up2.propname = 'lastName'" +
            "  left join o_userproperty up3 on up3.fk_user_id = u.user_id and up3.propname = 'email'" +
            " where s.creationdate >= :dateFrom and s.creationdate < :dateTo" +
            " order by s.creationdate desc",
            nativeQuery = true)
    List<Object[]> findByRepositoryEntryIdTimeBoundaries(@Param("repositoryEntryId") Long repositoryEntryId, @Param("dateFrom")Date dateFrom, @Param("dateTo") Date dateTo);
}
