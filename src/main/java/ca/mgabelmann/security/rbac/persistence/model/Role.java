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

    protected Role() {
        this(null, null, null, null, null, null, null, null, new ArrayList<>());
    }

    /** Constructor. */
    public Role(UUID createdBy, UUID modifiedBy, Instant createdOn, Instant modifiedOn, Long version, String code, String description, UUID id, List<OrganizationApplicationRole> organizationApplicationRoles) {
        super(createdBy, modifiedBy, createdOn, modifiedOn, version, code, description);
        this.id = id;
        this.organizationApplicationRoles = organizationApplicationRoles;
    }

    public  UUID getId() {
        return id;
    }

    public  void setId(final UUID id) {
        this.id = id;
    }

}
