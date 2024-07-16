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
import java.util.Objects;
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
    @Column(name = "LASTACCESS_DTM")
    private Instant lastAccessed;

    /** Records failed login attempts. Is reset when successful login occurs. */
    @Column(name = "LOGIN_ATTEMPTS", nullable = false, precision = 4, scale = 0)
    private Integer loginAttempts;

    /** When the account is locked due to issue(s). */
    @Convert(converter = BooleanConverter.class)
    @Column(name = "LOCKED", nullable = false, length = 1)
    private Boolean locked;

    @OneToMany(mappedBy = "userAccount")
    private List<UserApplicationRole> userApplicationRoles;


    /** Required by Spring/Hibernate. */
    protected UserAccount() {
        this(null, null, null, null, null, null, null, null, null);
    }

    /** Constructor. */
    public UserAccount(final UUID id, final String username, final String password, final String salt, final String pepper, final Boolean tempPassword, final Instant lastAccessed, final Integer loginAttempts, final Boolean locked) {
        this(null, null, Instant.now(), Instant.now(), 0L, id, username, password, salt, pepper, tempPassword, lastAccessed, loginAttempts, locked);
    }

    /** Constructor. */
    public UserAccount(final UUID createdBy, final UUID modifiedBy, final Instant createdOn, final Instant modifiedOn, final Long version, final UUID id, final String username, final String password, final String salt, final String pepper, final Boolean tempPassword, final Instant lastAccessed, final Integer loginAttempts, final Boolean locked) {
        super(createdBy, modifiedBy, createdOn, modifiedOn, version);
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.pepper = pepper;
        this.tempPassword = tempPassword;
        this.lastAccessed = lastAccessed;
        this.loginAttempts = loginAttempts;
        this.locked = locked;
        this.userApplicationRoles = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(final String salt) {
        this.salt = salt;
    }

    public String getPepper() {
        return pepper;
    }

    public void setPepper(final String pepper) {
        this.pepper = pepper;
    }

    public Boolean getTempPassword() {
        return tempPassword;
    }

    public void setTempPassword(final Boolean tempPassword) {
        this.tempPassword = tempPassword;
    }

    public Instant getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(final Instant lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    public Integer getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(final Integer loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(final Boolean locked) {
        this.locked = locked;
    }

    public List<UserApplicationRole> getUserOrganizationApplicationRoles() {
        return userApplicationRoles;
    }

    public void setUserOrganizationApplicationRoles(final List<UserApplicationRole> userApplicationRoles) {
        this.userApplicationRoles = userApplicationRoles;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", salt='" + salt + '\'' +
                ", lastAccessed=" + lastAccessed +
                ", loginAttempts=" + loginAttempts +
                ", locked=" + locked +
                ", tempPassword=" + tempPassword +
                //", password='" + password + '\'' +
                ", password='xxx'" +
                '}';
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount that)) return false;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
