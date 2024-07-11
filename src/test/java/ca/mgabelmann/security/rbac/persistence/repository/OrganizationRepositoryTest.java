package ca.mgabelmann.security.rbac.persistence.repository;

import ca.mgabelmann.security.rbac.persistence.model.Organization;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@DataJpaTest
public class OrganizationRepositoryTest {
    private OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationRepositoryTest(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Test
    void test1_() {
        Organization organization = OrganizationRepositoryTest.getOrganization();
        organizationRepository.save(organization);

        List<Organization> organizations = organizationRepository.findAll();
        Assertions.assertEquals(1, organizations.size());
    }



    public static Organization getOrganization() {
        UUID userId = UUID.randomUUID();
        Instant now = Instant.now();
        return new Organization(userId, userId, now, now, 0L, "ORG1", "description", UUID.randomUUID(), "Org 1", new ArrayList<>());
    }
}
