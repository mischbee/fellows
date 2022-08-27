package com.fellows.DBRepos;

import com.fellows.common.model.Fellowship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.UUID;
public interface FellowshipRepo extends JpaRepository<Fellowship, UUID>, JpaSpecificationExecutor<Fellowship> {
    @Query("SELECT f from T_FELLOWSHIP f WHERE f.institution.id = :institutionId ")
    Collection<Fellowship> findAllFellowshipsForInstitution(@Param("institutionId") UUID institutionId);

    @Query("SELECT f from T_FELLOWSHIP f WHERE UPPER(f.institution.name) = UPPER(:institutionName) ")
    Collection<Fellowship> findAllFellowshipsForInstitution(@Param("institutionName") String institutionName);

    @Query("SELECT t.fellowships from T_TAG t")
    Collection<Fellowship> findAllFellowshipsForTag(String tagName);
}
