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
@Table(name = "APPLICATION")
public class Application extends AbstractRbacCode implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @UuidGenerator
    @Column(name = "ID", nullable = false, unique = true)
    private UUID id;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @OneToMany(mappedBy = "application")
    private List<OrganizationApplication> organizationApplications;


    /** Required by Spring/Hibernate. */
    protected Application() {
        this(null, null, null, null, null, null, null, null, null);
    }

    /** Constructor. */
    public Application(final UUID createdBy, final UUID modifiedBy, final Instant createdOn, final Instant modifiedOn, final Long version, final String code, final String description, final UUID id, final String name) {
        super(createdBy, modifiedBy, createdOn, modifiedOn, version, code, description);
        this.id = id;
        this.name = name;
        this.organizationApplications = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OrganizationApplication> getOrganizationApplications() {
        return organizationApplications;
    }

    public  void setOrganizationApplications(final List<OrganizationApplication> organizationApplications) {
        this.organizationApplications = organizationApplications;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Application that)) return false;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
