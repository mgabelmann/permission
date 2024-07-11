package ca.mgabelmann.security.rbac.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;


/**
 *
 * @author mgabelmann
 */
@Entity
@Table(name = "APP_ROLE_ACTION")
public class ApplicationRoleAction extends AbstractRbacValue implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @UuidGenerator
    @Column(name = "ID", nullable = false, unique = true)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ORG_APP_ROLE_ID", nullable = false)
    private OrganizationApplicationRole orgAppRole;

    @ManyToOne
    @JoinColumn(name = "ACTION_ID", nullable = false)
    private Action action;


    /** Required by Spring/Hibernate. */
    protected ApplicationRoleAction() {
        this(null, null, null, null, null, null, null, null, null);
    }

    /** Constructor. */
    public ApplicationRoleAction(UUID createdBy, UUID modifiedBy, Instant createdOn, Instant modifiedOn, Long version, Boolean active, UUID id, OrganizationApplicationRole orgAppRole, Action action) {
        super(createdBy, modifiedBy, createdOn, modifiedOn, version, active);
        this.id = id;
        this.orgAppRole = orgAppRole;
        this.action = action;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public OrganizationApplicationRole getOrgAppRole() {
        return orgAppRole;
    }

    public void setOrgAppRole(final OrganizationApplicationRole orgAppRole) {
        this.orgAppRole = orgAppRole;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(final Action action) {
        this.action = action;
    }

    @Transient
    @Override
    public  String getRbacValue() {
        return orgAppRole.getRbacValue() + RBAC_VALUE_DELIMITER + action.getCode();
    }

    @Override
    public String toString() {
        return "ApplicationRoleAction{" +
                "id=" + id +
                ", orgAppRole=" + orgAppRole +
                ", action=" + action +
                ", active=" + active +
                '}';
    }

}
