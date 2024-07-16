package ca.mgabelmann.security.rbac.persistence.repository;

import ca.mgabelmann.security.rbac.persistence.model.Action;
import ca.mgabelmann.security.rbac.persistence.model.Application;
import ca.mgabelmann.security.rbac.persistence.model.ApplicationRoleAction;
import ca.mgabelmann.security.rbac.persistence.model.ModelFactory;
import ca.mgabelmann.security.rbac.persistence.model.Organization;
import ca.mgabelmann.security.rbac.persistence.model.OrganizationApplication;
import ca.mgabelmann.security.rbac.persistence.model.ApplicationRole;
import ca.mgabelmann.security.rbac.persistence.model.Role;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;


@DataJpaTest
class ApplicationRoleActionRepositoryTest {
    private final ApplicationRoleActionRepository applicationRoleActionRepository;
    private final EntityManager entityManager;

    private ApplicationRoleAction applicationRoleAction;


    @Autowired
    public ApplicationRoleActionRepositoryTest(final ApplicationRoleActionRepository applicationRoleActionRepository, final EntityManager entityManager) {
        this.applicationRoleActionRepository = applicationRoleActionRepository;
        this.entityManager = entityManager;
    }

    @BeforeEach
    void beforeEach() {
        Organization organization = ModelFactory.getOrganization("ORG_CODE", "ORG_DESCRIPTION", "ORG_NAME");
        Application application = ModelFactory.getApplication("APP_CODE", "APP_DESCRIPTION", "APP_NAME");
        OrganizationApplication organizationApplication = ModelFactory.getOrganizationApplication(organization, application);
        Role role = ModelFactory.getRole("ROLE_CODE", "ROLE_DESCRIPTION");
        ApplicationRole applicationRole = ModelFactory.getOrganizationApplicationRole(organizationApplication, role);
        Action action = ModelFactory.getAction("ACTION_CODE", "ACTION_DESCRIPTION");

        entityManager.persist(organization);
        entityManager.persist(application);
        entityManager.persist(organizationApplication);
        entityManager.persist(role);
        entityManager.persist(applicationRole);
        entityManager.persist(action);

        ApplicationRoleAction tmp = ModelFactory.getApplicationRoleAction(applicationRole, action);
        this.applicationRoleAction = applicationRoleActionRepository.save(tmp);
    }

    @Test
    @DisplayName("find by id - with result")
    void test1_findById() {
        Optional<ApplicationRoleAction> result = applicationRoleActionRepository.findById(applicationRoleAction.getId());
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    @DisplayName("get RBAC value")
    void test1_getRbacValue() {
        Assertions.assertEquals("ORG_CODE:APP_CODE:ROLE_CODE:ACTION_CODE", applicationRoleAction.getRbacValue());
    }

    @Test
    @DisplayName("findAllByActiveApplicationRole - with result")
    void test1_findAllByActiveApplicationRole() {
        List<ApplicationRoleAction> results = applicationRoleActionRepository.findAllByActiveApplicationRole("ORG_CODE", "APP_CODE", "ROLE_CODE", true);
        Assertions.assertEquals(1, results.size());
    }

    @Test
    @DisplayName("findAllByActiveApplicationRole - without result")
    void test2_findAllByActiveApplicationRole() {
        List<ApplicationRoleAction> results = applicationRoleActionRepository.findAllByActiveApplicationRole("ORG_CODE", "APP_CODE", "ROLE_CODE", false);
        Assertions.assertEquals(0, results.size());
    }

}