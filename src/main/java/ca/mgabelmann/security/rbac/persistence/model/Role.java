package ca.mgabelmann.security.rbac.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "ROLE")
public class Role extends AbstractRbacCode implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @UuidGenerator
    @Column(name = "ID", nullable = false, unique = true)
    private UUID id;

    @OneToMany(mappedBy = "role")
    private List<OrganizationApplicationRole> organizationApplicationRoles;


    /** Required by Spring/Hibernate. */
    protected Role() {
        this(null, null, null, null, null, null, null, null);
    }

    /** Constructor. */
    public Role(final UUID createdBy, final UUID modifiedBy, final Instant createdOn, final Instant modifiedOn, final Long version, final String code, final String description, final UUID id) {
        super(createdBy, modifiedBy, createdOn, modifiedOn, version, code, description);
        this.id = id;
        this.organizationApplicationRoles = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public List<OrganizationApplicationRole> getOrganizationApplicationRoles() {
        return organizationApplicationRoles;
    }

    public void setOrganizationApplicationRoles(final List<OrganizationApplicationRole> organizationApplicationRoles) {
        this.organizationApplicationRoles = organizationApplicationRoles;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role role)) return false;

        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
