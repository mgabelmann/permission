package ca.mgabelmann.security.rbac.persistence.repository;

import ca.mgabelmann.security.rbac.persistence.model.Application;
import ca.mgabelmann.security.rbac.persistence.model.ApplicationRoleAction;
import ca.mgabelmann.security.rbac.persistence.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ApplicationRoleActionRepository extends JpaRepository<ApplicationRoleAction, UUID> {

    //List<ApplicationRoleAction> findByOrganizationAndApplicationAndActive(Organization organization, Application application, boolean active);

}

