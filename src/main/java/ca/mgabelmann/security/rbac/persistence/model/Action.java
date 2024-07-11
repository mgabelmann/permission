package ca.mgabelmann.security.rbac.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.annotations.Comment;
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
@Table(name = "ACTION")
@Comment("similar to a permission at the application or page level")
public class Action extends AbstractRbacCode implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @UuidGenerator
    @Column(name = "ID", nullable = false, unique = true)
    private UUID id;

    @OneToMany(mappedBy = "action")
    private List<ApplicationRoleAction> applicationRoleActions;


    /** Required by Spring/Hibernate. */
    protected Action() {
        this(null, null, null, null, null, null, null, null, new ArrayList<>());
    }

    /** Constructor. */
    public Action(final UUID createdBy, final UUID modifiedBy, final Instant createdOn, final Instant modifiedOn, final Long version, final String code, final String description, final UUID id, final List<ApplicationRoleAction> applicationRoleActions) {
        super(createdBy, modifiedBy, createdOn, modifiedOn, version, code, description);
        this.id = id;
        this.applicationRoleActions = applicationRoleActions;
    }

    public  UUID getId() {
        return id;
    }

    public  void setId(final UUID id) {
        this.id = id;
    }

    public  List<ApplicationRoleAction> getApplicationRoleActions() {
        return applicationRoleActions;
    }

    public  void setApplicationRoleActions(final List<ApplicationRoleAction> applicationRoleActions) {
        this.applicationRoleActions = applicationRoleActions;
    }

}
