package ca.mgabelmann.security.rbac.persistence.model;

import java.time.Instant;
import java.util.UUID;


/** Used to create model objects for testing purposes. */
public class ModelFactory {
    public static UUID userId = UUID.randomUUID();
    public static Instant now = Instant.now();


    public static Action getAction(final String code, final String description) {
        return new Action(userId, userId, now, now, 0L, code, description, null);
    }

    public static Application getApplication(final String code, final String description, final String name) {
        return new Application(userId, userId, now, now, 0L, code, description, null, name);
    }

    public static ApplicationRoleAction getApplicationRoleAction(final ApplicationRole applicationRole, final Action action) {
        return new ApplicationRoleAction(userId, userId, now, now, 0L, Boolean.TRUE, null, applicationRole, action);
    }

    public static Organization getOrganization(final String code, final String description, final String name) {
        return new Organization(userId, userId, now, now, 0L, code, description, null, name);
    }

    public static OrganizationApplication getOrganizationApplication(final Organization organization, final Application application) {
        return new OrganizationApplication(userId, userId, now, now, 0L, Boolean.TRUE, null, organization, application);
    }

    public static ApplicationRole getOrganizationApplicationRole(final OrganizationApplication organizationApplication, final Role role) {
        return new ApplicationRole(userId, userId, now, now, 0L, Boolean.TRUE, null, organizationApplication, role);
    }

    public static Role getRole(final String code, final String description) {
        return new Role(userId, userId, now, now, 0L, code, description, null);
    }

    public static UserAccount getUserAccount(final String username, final String password, final boolean tempPassword) {
        Instant lastAccessed = Instant.now();
        boolean locked = false;
        String salt = UUID.randomUUID().toString();
        String pepper = UUID.randomUUID().toString();
        Integer loginAttempts = 0;
        return new UserAccount(userId, userId, now, now, 0L, null, username, password, salt, pepper, tempPassword, lastAccessed, loginAttempts, locked);
    }

    public static UserApplicationRole getUserOrganizationApplicationRole(final UserAccount userAccount, final ApplicationRole applicationRole) {
        return new UserApplicationRole(userId, userId, now, now, 0L, null, userAccount, applicationRole);
    }

}
