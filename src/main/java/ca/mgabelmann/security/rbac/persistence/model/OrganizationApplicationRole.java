package ca.mgabelmann.security.rbac.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


/**
 *
 * @author mgabelmann
 */
@Entity
@Table(name = "ORG_APP_ROLE")
public class OrganizationApplicationRole extends AbstractRbacValue implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @UuidGenerator
    @Column(name = "ID", nullable = false, unique = true)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ORG_APP_ID", nullable = false)
    private OrganizationApplication organizationApplication;

    @ManyToOne
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "organizationApplicationRole")
    private List<UserOrganizationApplicationRole> userOrganizationApplicationRoles;

    @OneToMany(mappedBy = "orgAppRole")
    private List<ApplicationRoleAction> applicationRoleActions;


    /** Required by Spring/Hibernate. */
    protected OrganizationApplicationRole() {
        this(null, null, null, null, null, null, null, null, null);
    }

    /** Constructor. */
    public OrganizationApplicationRole(final UUID createdBy, final UUID modifiedBy, final Instant createdOn, final Instant modifiedOn, final Long version, final Boolean active, final UUID id, final OrganizationApplication organizationApplication, final Role role) {
        super(createdBy, modifiedBy, createdOn, modifiedOn, version, active);
        this.id = id;
        this.organizationApplication = organizationApplication;
        this.role = role;
        this.userOrganizationApplicationRoles = new ArrayList<>();
        this.applicationRoleActions = new ArrayList<>();
    }

    public  UUID getId() {
        return id;
    }

    public  void setId(final UUID id) {
        this.id = id;
    }

    public  OrganizationApplication getOrganizationApplication() {
        return organizationApplication;
    }

    public  void setOrganizationApplication(final OrganizationApplication organizationApplication) {
        this.organizationApplication = organizationApplication;
    }

    public  Role getRole() {
        return role;
    }

    public  void setRole(final Role role) {
        this.role = role;
    }

    public  List<UserOrganizationApplicationRole> getUserOrganizationApplicationRoles() {
        return userOrganizationApplicationRoles;
    }

    public  void setUserOrganizationApplicationRoles(final List<UserOrganizationApplicationRole> userOrganizationApplicationRoles) {
        this.userOrganizationApplicationRoles = userOrganizationApplicationRoles;
    }

    public  List<ApplicationRoleAction> getApplicationRoleActions() {
        return applicationRoleActions;
    }

    public  void setApplicationRoleActions(final List<ApplicationRoleAction> applicationRoleActions) {
        this.applicationRoleActions = applicationRoleActions;
    }

    @Override
    public String getRbacValue() {
        return organizationApplication.getRbacValue() + RBAC_VALUE_DELIMITER + role.getCode();
    }

    @Override
    public String toString() {
        return "OrganizationApplicationRole{" +
                "id=" + id +
                ", organizationApplication=" + organizationApplication +
                ", role=" + role +
                ", active=" + active +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrganizationApplicationRole that)) return false;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
