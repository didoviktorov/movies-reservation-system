package org.movies.system.areas.halls.repositories;

import org.movies.system.areas.halls.entities.Hall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepository extends JpaRepository<Hall, String> {

    Hall findFirstById(String id);

}
