package org.movies.system.repositories;

import org.movies.system.models.entities.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepository extends JpaRepository<Hall, String> {

    Hall findFirstById(String id);

}
