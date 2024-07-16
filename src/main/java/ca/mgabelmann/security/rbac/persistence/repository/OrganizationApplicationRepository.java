package ca.mgabelmann.security.rbac.persistence.repository;

import ca.mgabelmann.security.rbac.persistence.model.OrganizationApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface OrganizationApplicationRepository extends JpaRepository<OrganizationApplication, UUID> {

    /**
     * Get All active organization applications for the given organization code.
     * @param orgCode org code
     * @param active is active
     * @return records
     */
    @Query("SELECT oa FROM OrganizationApplication oa LEFT JOIN oa.organization o WHERE o.code = :orgCode AND oa.active = :active")
    List<OrganizationApplication> findAllByActiveOrgCode(@Param("orgCode") String orgCode, @Param("active") boolean active);

}
