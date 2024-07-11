package ca.mgabelmann.security.rbac.persistence.model;

import java.time.Instant;

/**
 *
 * @author mgabelmann
 */
public interface DateTimeActive {

    Instant getStartDtm();
    void setStartDtm(Instant startDtm);

    Instant getEndDtm();
    void setEndDtm(Instant endDtm);

    /**
     * Is effective during the date and time given.
     * @param datetime date and time
     * @return true if effective, false otherwise
     */
    boolean isEffective(Instant datetime);

    /**
     * Is active using current date and time.
     * @return true if active, false otherwise
     */
    boolean isActive();

}
