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
import java.util.Objects;
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
        this(null, null);
    }

    /** Constructor. */
    public UserOrganizationApplicationRole(final UserAccount userAccount, final OrganizationApplicationRole organizationApplicationRole) {
        this(null, null, Instant.now(), Instant.now(), 0L, null, userAccount, organizationApplicationRole);
    }

    /** Constructor. */
    public UserOrganizationApplicationRole(final UUID createdBy, final UUID modifiedBy, final Instant createdOn, final Instant modifiedOn, final Long version, final UUID id, final UserAccount userAccount, final OrganizationApplicationRole organizationApplicationRole) {
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

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserOrganizationApplicationRole that)) return false;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
