package ca.mgabelmann.security.rbac.persistence.repository;

import ca.mgabelmann.security.rbac.persistence.model.ApplicationRoleAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface ApplicationRoleActionRepository extends JpaRepository<ApplicationRoleAction, UUID> {

    /**
     * Find all application role actions that are active for given organization, application and role.
     * @param orgCode organization code
     * @param appCode application code
     * @param roleCode role code
     * @param active is active
     * @return records
     */
    @Query("SELECT ara FROM ApplicationRoleAction ara LEFT JOIN ara.appRole ar LEFT JOIN ar.role r LEFT JOIN ar.organizationApplication oa LEFT JOIN oa.organization o LEFT JOIN oa.application a WHERE ara.active = :active AND ar.active = :active AND oa.active = :active AND r.code = :roleCode AND o.code = :orgCode AND a.code = :appCode")
    List<ApplicationRoleAction> findAllByActiveApplicationRole(@Param("orgCode") String orgCode, @Param("appCode") String appCode, @Param("roleCode") String roleCode, @Param("active") boolean active);

}

