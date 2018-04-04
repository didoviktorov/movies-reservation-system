package org.movies.system.repositories;

import org.movies.system.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {

    Role findFirstByName(String name);
}
