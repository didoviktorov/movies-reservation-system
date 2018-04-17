package org.movies.system.repositories;

import org.movies.system.models.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {

    Movie findFirstById(String id);

    Page<Movie> findAllByDeletedOnNull(Pageable pageable);
}
