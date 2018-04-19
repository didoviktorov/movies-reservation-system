package org.movies.system.areas.roles.services;

import org.movies.system.areas.roles.entities.Role;

import java.util.List;

public interface RoleService {

    Role findFirstByName(String name);

    void save(Role role);

    List<Role> findAllRoles();

    Long roleCount();

    void seedRoles();
}
