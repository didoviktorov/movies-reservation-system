package org.movies.system.repositories;

import org.movies.system.models.entities.Projection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Repository
public interface ProjectionRepository extends JpaRepository<Projection, String> {

    Projection findFirstById(String id);

    Page<Projection> findAllByDeletedOnNullAndMovieDeletedOnNull(Pageable pageable);

    Page<Projection> findAllByCinemaIdAndDeletedOnNull(String cinema_id, Pageable pageable);
}
