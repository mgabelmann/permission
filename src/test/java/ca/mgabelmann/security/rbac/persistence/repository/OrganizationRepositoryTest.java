package ca.mgabelmann.security.rbac.persistence.repository;

import ca.mgabelmann.security.rbac.persistence.model.Organization;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@DataJpaTest
public class OrganizationRepositoryTest {
    private final OrganizationRepository organizationRepository;

    private Organization organization;

    @Autowired
    public OrganizationRepositoryTest(final OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @BeforeEach
    void beforeEach() {
        Organization tmp = OrganizationRepositoryTest.getOrganization();
        this.organization = organizationRepository.save(tmp);
    }

    @Test
    void test1_findAll() {
        List<Organization> organizations = organizationRepository.findAll();
        Assertions.assertEquals(1, organizations.size());
    }

    @Test
    void test2_findById() {
        Optional<Organization> result = organizationRepository.findById(organization.getId());
        Assertions.assertTrue(result.isPresent());
    }

    public static Organization getOrganization() {
        UUID userId = UUID.randomUUID();
        Instant now = Instant.now();
        return new Organization(userId, userId, now, now, 0L, "ORG1", "description", null, "Org 1", new ArrayList<>());
    }

}
