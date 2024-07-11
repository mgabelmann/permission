package ca.mgabelmann.security.rbac.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

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
@Table(name = "USER_ACCOUNT")
public class UserAccount extends AbstractAuditable implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /** Unique id. */
    @Id
    @UuidGenerator
    @Column(name = "ID", nullable = false, unique = true)
    private UUID id;

    /** Username is typically the users email address. */
    @Column(name = "USERNAME", nullable = false, unique = true, length = 320)
    private String username;

    /** Hashed value using salt and pepper. */
    @Column(name = "PASSWORD", nullable = false, length = 32)
    private String password;

    /** 'Salt' for hashing password which is unique to a user. */
    @Column(name = "SALT", unique = true, nullable = false, length = 32)
    private String salt;

    /**
     * This value is used in conjunction with a salt to hash a user's password.
     * Salt is unique to a users account and pepper is not stored in the DB and
     * is application specific. Recommended to be a hash and loaded via
     * environment variable.
     */
    @Transient
    private String pepper;

    @Convert(converter = BooleanConverter.class)
    @Column(name = "TEMP_PASSWORD", nullable = false, length = 1)
    private Boolean tempPassword;

    /** Records the last time this user logged into the system. May be null. */
    @Column(name = "LOGIN_DTM")
    private Instant loginOn;

    /** Records failed login attempts. Is reset when successful login occurs. */
    @Column(name = "LOGIN_ATTEMPTS", nullable = false, precision = 4, scale = 0)
    private Integer loginAttempts;

    /** When the account is locked due to issue(s). */
    @Column(name = "LOCKED_DTM")
    private Instant lockedOn;

    @OneToMany(mappedBy = "userAccount")
    private List<UserOrganizationApplicationRole> userOrganizationApplicationRoles;


    /** Required by Spring/Hibernate. */
    protected UserAccount() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, new ArrayList<>());
    }

    public UserAccount(UUID createdBy, UUID modifiedBy, Instant createdOn, Instant modifiedOn, Long version, UUID id, String username, String password, String salt, String pepper, Boolean tempPassword, Instant loginOn, Integer loginAttempts, Instant lockedOn, List<UserOrganizationApplicationRole> userOrganizationApplicationRoles) {
        super(createdBy, modifiedBy, createdOn, modifiedOn, version);
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.pepper = pepper;
        this.tempPassword = tempPassword;
        this.loginOn = loginOn;
        this.loginAttempts = loginAttempts;
        this.lockedOn = lockedOn;
        this.userOrganizationApplicationRoles = userOrganizationApplicationRoles;
    }

    //    /** Is the account activated. */
//    @Transient
//    public boolean isActive() {
//        return loginOn != null || (tempPassword != null && !tempPassword);
//    }
//
//    @Transient
//    public boolean isLocked() {
//        return lockedOn != null || loginOn == null;
//    }
//
//    @Transient
//    public void resetLoginAttempts() {
//        loginAttempts = 0;
//    }
//
//    @Transient
//    public void incrementLoginAttempts() {
//        loginAttempts++;
//    }


    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", salt='" + salt + '\'' +
                ", loginOn=" + loginOn +
                ", loginAttempts=" + loginAttempts +
                ", lockedOn=" + lockedOn +
                ", tempPassword=" + tempPassword +
                //", password='" + password + '\'' +
                ", password='xxx'" +
                '}';
    }
}
