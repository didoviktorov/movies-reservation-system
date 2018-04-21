package org.movies.system.areas.projections.repositories;

import org.movies.system.areas.projections.entities.Projection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectionRepository extends JpaRepository<Projection, String> {

    Projection findFirstById(String id);

    Page<Projection> findAllByDeletedOnNullAndMovieDeletedOnNull(Pageable pageable);

    List<Projection> findAllByDeletedOnNullAndMovieDeletedOnNull();

    Page<Projection> findAllByCinemaNameAndDeletedOnNullAndMovieDeletedOnNull(String cinemaName, Pageable pageable);
}
