package com.fellows.DBRepos;

import com.fellows.common.model.Fellowship;
import com.fellows.common.model.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.UUID;

public interface InstitutionRepo extends JpaRepository<Institution, UUID> {
    @Query("SELECT f from T_FELLOWSHIP f WHERE f.institution.id = :institutionId ")
    Collection<Fellowship> findAllFellowshipsForInstitution(@Param("institutionId") UUID institutionId);
}
