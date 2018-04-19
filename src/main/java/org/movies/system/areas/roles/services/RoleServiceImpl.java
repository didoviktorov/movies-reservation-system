package org.movies.system.areas.roles.services;

import org.movies.system.areas.roles.entities.Role;
import org.movies.system.areas.roles.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findFirstByName(String name) {
        return this.roleRepository.findFirstByName(name);
    }

    @Override
    public void save(Role role) {
        this.roleRepository.save(role);
    }

    @Override
    public List<Role> findAllRoles() {
        return this.roleRepository.findAll();
    }

    @Override
    public Long roleCount() {
        return this.roleRepository.count();
    }

    @Override
    public void seedRoles() {
        Role userRole = new Role();
        userRole.setName("USER");

        Role adminRole = new Role();
        adminRole.setName("ADMIN");

        this.roleRepository.save(userRole);
        this.roleRepository.save(adminRole);
    }
}
