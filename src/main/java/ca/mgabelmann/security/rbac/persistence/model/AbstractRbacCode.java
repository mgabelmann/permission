package ca.mgabelmann.security.rbac.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;


/**
 *
 * @author mgabelmann
 */
@MappedSuperclass
public abstract class AbstractRbacCode extends AbstractAuditable implements Serializable {

    @Column(name = "CODE", nullable = false, unique = true, length = 30)
    protected String code;

    @Column(name = "DESCRIPTION", length = 512)
    protected String description;


    /**
     * Constructor.
     * @param createdBy created by
     * @param modifiedBy modified by
     * @param createdOn created on
     * @param modifiedOn modified on
     * @param version version
     * @param code
     * @param description
     */
    public AbstractRbacCode(final UUID createdBy, final UUID modifiedBy, final Instant createdOn, final Instant modifiedOn, final Long version, final String code, final String description) {
        super(createdBy, modifiedBy, createdOn, modifiedOn, version);
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

}
