package ca.mgabelmann.security.rbac.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 *
 * @author mgabelmann
 */
@Entity
@Table(name = "USER_ORG_APP_ROLE")
public class UserOrganizationApplicationRole extends AbstractAuditable implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @UuidGenerator
    @Column(name = "ID", nullable = false, unique = true)
    private UUID id;

    @ManyToOne
    private UserAccount userAccount;

    @ManyToMany(mappedBy = "userOrganizationApplicationRoles")
    private List<OrganizationApplicationRole> organizationApplicationRoles;

    /** Required by Spring/Hibernate. */
    protected UserOrganizationApplicationRole() {
        this(null, null, null, null, null, null, null, new ArrayList<>());
    }

    /** Constructor. */
    public UserOrganizationApplicationRole(UUID createdBy, UUID modifiedBy, Instant createdOn, Instant modifiedOn, Long version, UUID id, UserAccount userAccount, List<OrganizationApplicationRole> organizationApplicationRoles) {
        super(createdBy, modifiedBy, createdOn, modifiedOn, version);
        this.id = id;
        this.userAccount = userAccount;
        this.organizationApplicationRoles = organizationApplicationRoles;
    }

    public  UUID getId() {
        return id;
    }

    public  void setId(final UUID id) {
        this.id = id;
    }

    public  UserAccount getUserAccount() {
        return userAccount;
    }

    public  void setUserAccount(final UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public  List<OrganizationApplicationRole> getOrganizationApplicationRoles() {
        return organizationApplicationRoles;
    }

    public  void setOrganizationApplicationRoles(final List<OrganizationApplicationRole> organizationApplicationRoles) {
        this.organizationApplicationRoles = organizationApplicationRoles;
    }

}
