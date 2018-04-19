package org.movies.system.areas.roles.repositories;

import org.movies.system.areas.roles.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {

    Role findFirstByName(String name);
}
