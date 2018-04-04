package org.movies.system.services;

import org.movies.system.models.entities.Role;

import java.util.List;

public interface RoleService {

    Role findFirstByName(String name);

    void save(Role role);

    List<Role> findAllRoles();

    Long roleCount();

    void seedRoles();
}
