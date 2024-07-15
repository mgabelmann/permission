package ca.mgabelmann.security.rbac.persistence.repository;

import ca.mgabelmann.security.rbac.persistence.model.ModelFactory;
import ca.mgabelmann.security.rbac.persistence.model.Role;
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
public class RoleRepositoryTest {
    private RoleRepository roleRepository;

    private Role role;

    @Autowired
    public RoleRepositoryTest(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @BeforeEach
    void beforeEach() {
        Role tmp = ModelFactory.getRole("code", "description");
        this.role = roleRepository.save(tmp);
    }

    @Test
    @DisplayName("find by primary key - result")
    void test1_findById() {
        Optional<Role> result = roleRepository.findById(role.getId());
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    @DisplayName("find all - result")
    void test1_findAll() {
        List<Role> results = roleRepository.findAll();
        Assertions.assertEquals(1, results.size());
    }

    @Test
    @DisplayName("find by code - result")
    void test1_findByCode() {
        Optional<Role> result = roleRepository.findByCode(role.getCode());
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    @DisplayName("find by code - no results")
    void test2_findByCode() {
        Optional<Role> result = roleRepository.findByCode("code2");
        Assertions.assertFalse(result.isPresent());
    }



}
