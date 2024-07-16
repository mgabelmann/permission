package ca.mgabelmann.security.rbac.persistence.repository;

import ca.mgabelmann.security.rbac.persistence.model.Application;
import ca.mgabelmann.security.rbac.persistence.model.ModelFactory;
import ca.mgabelmann.security.rbac.persistence.model.Organization;
import ca.mgabelmann.security.rbac.persistence.model.OrganizationApplication;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class OrganizationApplicationRepositoryTest {
    private final OrganizationApplicationRepository organizationApplicationRepository;
    private final EntityManager entityManager;

    @Autowired
    public OrganizationApplicationRepositoryTest(final OrganizationApplicationRepository organizationApplicationRepository, final EntityManager entityManager) {
        this.organizationApplicationRepository = organizationApplicationRepository;
        this.entityManager = entityManager;
    }

    @BeforeEach
    void beforeEach() {
        Organization tmp1 = ModelFactory.getOrganization("ORG1", "description", "name");
        Application tmp2 = ModelFactory.getApplication("APP1", "description1", "name1");
        Application tmp3 = ModelFactory.getApplication("APP2", "description2", "name2");
        OrganizationApplication tmp4 = ModelFactory.getOrganizationApplication(tmp1, tmp2);
        OrganizationApplication tmp5 = ModelFactory.getOrganizationApplication(tmp1, tmp3);
        tmp5.setActive(false);

        entityManager.persist(tmp1);
        entityManager.persist(tmp2);
        entityManager.persist(tmp3);
        entityManager.persist(tmp4);
        entityManager.persist(tmp5);
    }

    @Test
    @DisplayName("findAllByActiveCode - active only")
    void test1_findAllByActiveOrgCode() {
        List<OrganizationApplication> results = organizationApplicationRepository.findAllByActiveOrgCode("ORG1", true);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("APP1", results.get(0).getApplication().getCode());
    }

    @Test
    @DisplayName("findAllByActiveCode - inactive only")
    void test2_findAllByActiveOrgCode() {
        List<OrganizationApplication> results = organizationApplicationRepository.findAllByActiveOrgCode("ORG1", false);
        Assertions.assertEquals(1, results.size());
        Assertions.assertEquals("APP2", results.get(0).getApplication().getCode());
    }

}