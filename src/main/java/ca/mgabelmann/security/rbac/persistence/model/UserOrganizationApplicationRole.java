package ca.mgabelmann.security.rbac.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;


/**
 * This is a ManyToMany object for UserAccount and OrganizationApplicationRole.
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
    @JoinColumn(name = "USER_ACCOUNT_ID")
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "ORG_APP_ROLE_ID")
    private OrganizationApplicationRole organizationApplicationRole;


    /** Required by Spring/Hibernate. */
    protected UserOrganizationApplicationRole() {
        this(null, null, null, null, null, null, null, null);
    }

    /** Constructor. */
    public UserOrganizationApplicationRole(UUID createdBy, UUID modifiedBy, Instant createdOn, Instant modifiedOn, Long version, UUID id, UserAccount userAccount, OrganizationApplicationRole organizationApplicationRole) {
        super(createdBy, modifiedBy, createdOn, modifiedOn, version);
        this.id = id;
        this.userAccount = userAccount;
        this.organizationApplicationRole = organizationApplicationRole;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(final UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public OrganizationApplicationRole getOrganizationApplicationRole() {
        return organizationApplicationRole;
    }

    public void setOrganizationApplicationRole(final OrganizationApplicationRole organizationApplicationRole) {
        this.organizationApplicationRole = organizationApplicationRole;
    }

    @Override
    public String toString() {
        return "UserOrganizationApplicationRole{" +
                "id=" + id +
                ", userAccount=" + userAccount +
                '}';
    }
}
