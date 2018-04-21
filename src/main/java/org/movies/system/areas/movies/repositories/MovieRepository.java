package org.movies.system.areas.movies.repositories;

import org.movies.system.areas.movies.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {

    Movie findFirstById(String id);

    Movie findFirstByTitle(String title);

    Page<Movie> findAllByDeletedOnNull(Pageable pageable);

    Page<Movie> findAllByTitleContainingAndDeletedOnNull(String title, Pageable pageable);

    List<Movie> findAllByDeletedOnNull();
}
