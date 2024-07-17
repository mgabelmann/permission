package ca.mgabelmann.security.rbac.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;


/**
 *
 * @author mgabelmann
 */
@MappedSuperclass
public abstract class AbstractRbacValue extends AbstractAuditable implements BooleanActive, Serializable {
    protected static final String RBAC_VALUE_DELIMITER = ":";

    @Convert(converter = BooleanConverter.class)
    @Column(name = "ACTIVE", nullable = false, length = 1)
    protected Boolean active;


    /**
     * Constructor.
     * @param createdBy created by
     * @param modifiedBy modified by
     * @param createdOn created on
     * @param modifiedOn modified on
     * @param version version
     * @param active
     */
    public AbstractRbacValue(final UUID createdBy, final UUID modifiedBy, final Instant createdOn, final Instant modifiedOn, final Long version, final Boolean active) {
        super(createdBy, modifiedBy, createdOn, modifiedOn, version);
        this.active = active;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(final Boolean active) {
        this.active = active;
    }

    @Transient
    public boolean isActive() {
        return active;
    }

    /**
     * Unique path value for this
     * @return delimited string
     */
    public abstract String getRbacValue();

}
