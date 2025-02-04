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
import java.util.Objects;
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
    @JoinColumn(name = "APP_ROLE_ID", nullable = false)
    private ApplicationRole appRole;

    @ManyToOne
    @JoinColumn(name = "ACTION_ID", nullable = false)
    private Action action;


    /** Required by Spring/Hibernate. */
    protected ApplicationRoleAction() {
        this(Boolean.TRUE, null, null, null);
    }

    /** Constructor. */
    public ApplicationRoleAction(final Boolean active, final UUID id, final ApplicationRole appRole, final Action action) {
        this(null, null, Instant.now(), Instant.now(), 0L, active, id, appRole, action);
    }

    /** Constructor. */
    public ApplicationRoleAction(final UUID createdBy, final UUID modifiedBy, final Instant createdOn, final Instant modifiedOn, final Long version, final Boolean active, final UUID id, final ApplicationRole appRole, final Action action) {
        super(createdBy, modifiedBy, createdOn, modifiedOn, version, active);
        this.id = id;
        this.appRole = appRole;
        this.action = action;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public ApplicationRole getAppRole() {
        return appRole;
    }

    public void setAppRole(final ApplicationRole appRole) {
        this.appRole = appRole;
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
        return appRole.getRbacValue() + RBAC_VALUE_DELIMITER + action.getCode();
    }

    @Override
    public String toString() {
        return "ApplicationRoleAction{" +
                "id=" + id +
                ", appRole=" + appRole +
                ", action=" + action +
                ", active=" + active +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApplicationRoleAction that)) return false;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
