package org.movies.system.repositories;

import org.movies.system.models.entities.Projection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectionRepository extends JpaRepository<Projection, String> {

    Projection findFirstById(String id);
}
