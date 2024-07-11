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
import java.util.UUID;


/**
 *
 * @author mgabelmann
 */
@Entity
@Table(name = "ORG_APP")
public class OrganizationApplication extends AbstractRbacValue implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @UuidGenerator
    @Column(name = "ID", nullable = false, unique = true)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ORG_ID", nullable = false)
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "APP_ID", nullable = false)
    private Application application;

    @OneToMany(mappedBy = "organizationApplication")
    private List<OrganizationApplicationRole> organizationApplicationRoles;


//    /** Required by Spring/Hibernate. */
//    protected OrganizationApplication() {
//        this(null, null, null, new ArrayList<>());
//    }
//
//    /**
//     * Constructor. All args.
//     * @param id
//     * @param organization
//     * @param application
//     */
//    public OrganizationApplication(final UUID id, final Organization organization, final Application application, final List<OrganizationApplicationRole> organizationApplicationRoles) {
//        this.id = id;
//        this.organization = organization;
//        this.application = application;
//        this.organizationApplicationRoles = organizationApplicationRoles;
//    }

    protected OrganizationApplication() {
        this(null, null, null, null, null, null, null, null, null, new ArrayList<>());
    }

    public OrganizationApplication(UUID createdBy, UUID modifiedBy, Instant createdOn, Instant modifiedOn, Long version, Boolean active, UUID id, Organization organization, Application application, List<OrganizationApplicationRole> organizationApplicationRoles) {
        super(createdBy, modifiedBy, createdOn, modifiedOn, version, active);
        this.id = id;
        this.organization = organization;
        this.application = application;
        this.organizationApplicationRoles = organizationApplicationRoles;
    }

    public  UUID getId() {
        return id;
    }

    public  void setId(final UUID id) {
        this.id = id;
    }

    public  Organization getOrganization() {
        return organization;
    }

    public  void setOrganization(final Organization organization) {
        this.organization = organization;
    }

    public  Application getApplication() {
        return application;
    }

    public  void setApplication(final Application application) {
        this.application = application;
    }

    public  List<OrganizationApplicationRole> getOrganizationApplicationRoles() {
        return organizationApplicationRoles;
    }

    public  void setOrganizationApplicationRoles(final List<OrganizationApplicationRole> organizationApplicationRoles) {
        this.organizationApplicationRoles = organizationApplicationRoles;
    }


    @Override
    public String getRbacValue() {
        return organization.getCode() + RBAC_VALUE_DELIMITER + application.getCode();
    }

    @Override
    public String toString() {
        return "OrganizationApplication{" +
                "id=" + id +
                ", organization=" + organization +
                ", application=" + application +
                ", active=" + active +
                '}';
    }

}
