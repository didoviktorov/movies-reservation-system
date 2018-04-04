package org.movies.system.repositories;

import org.movies.system.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findFirstByUsername(String username);

    User findFirstById(String id);
}
