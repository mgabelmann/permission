package ca.mgabelmann.security.rbac.persistence.repository;

import ca.mgabelmann.security.rbac.persistence.model.UserApplicationRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserApplicationRoleRepository extends JpaRepository<UserApplicationRole, UUID> {

}
