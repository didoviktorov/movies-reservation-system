package org.movies.system.services.cinema;

import org.movies.system.models.binding.ProjectionBinding;
import org.movies.system.models.binding.ProjectionEditBinding;
import org.movies.system.models.entities.Cinema;
import org.movies.system.models.entities.Projection;
import org.movies.system.models.view.ProjectionViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProjectionService {

    Long projectionsCount();

    void save(ProjectionBinding projectionBinding);

    void edit(String id, ProjectionEditBinding projectionEditBinding);

    Page<Projection> findAllProjections(Pageable pageable);

    Projection findById(String id);

    ProjectionViewDto findProjectionView(String id);

    Page<Projection> findAllByCinema_Id(String cinema_id, Pageable pageable);

    ProjectionEditBinding getEditProjection(String id);

    void delete(String id);

    boolean validProjection(ProjectionBinding projectionBinding, ProjectionEditBinding projectionEditBinding, String editId);
}
