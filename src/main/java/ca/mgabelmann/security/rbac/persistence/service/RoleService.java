package ca.mgabelmann.security.rbac.persistence.service;

import ca.mgabelmann.security.rbac.persistence.model.Role;
import ca.mgabelmann.security.rbac.persistence.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(final RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Role> findByCode(final String code) {
        return roleRepository.findByCode(code);
    }

}
