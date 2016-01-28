package ru.sav.saucemanager.repository;

import ru.sav.saucemanager.domain.QtiResultSet;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Resource
public interface QtiResultSetRepository extends CrudRepository<QtiResultSet, Long>{
    @Query(value = "select qti.* " +
            "from o_qtiresultset qti " +
            "inner join o_olatresource re on re.resid = qti.olatresource_fk " +
            "inner join o_repositoryentry rep on rep.fk_olatresource = re.resource_id " +
            "where rep.repositoryentry_id = :id " +
            "order by qti.resultset_id",
            nativeQuery = true)
    List<QtiResultSet> findByRepositoryEntryId(@Param("id") Long id);

    @Query(value = "select qti.* " +
            " from o_qtiresultset qti " +
            "  inner join o_olatresource re on re.resid = qti.olatresource_fk " +
            "  inner join o_repositoryentry rep on rep.fk_olatresource = re.resource_id " +
            " where rep.repositoryentry_id = :id " +
            "  and qti.creationdate >= :dateFrom " +
            " order by qti.resultset_id",
            nativeQuery = true)
    List<QtiResultSet> findByRepositoryEntryIdLaterThen(@Param("id") Long id, @Param("dateFrom")Date dateFrom);
}
