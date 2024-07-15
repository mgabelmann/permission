package ca.mgabelmann.security.rbac.persistence.repository;

import ca.mgabelmann.security.rbac.persistence.model.ModelFactory;
import ca.mgabelmann.security.rbac.persistence.model.Organization;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.Instant;
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
        Organization tmp = ModelFactory.getOrganization("code", "description", "name");
        this.organization = organizationRepository.save(tmp);
    }

    @Test
    @DisplayName("find all - results")
    void test1_findAll() {
        List<Organization> organizations = organizationRepository.findAll();
        Assertions.assertEquals(1, organizations.size());
    }

    @Test
    @DisplayName("find by primary key - result")
    void test2_findById() {
        Optional<Organization> result = organizationRepository.findById(organization.getId());
        Assertions.assertTrue(result.isPresent());
    }



}
