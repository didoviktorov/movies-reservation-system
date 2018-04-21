package org.movies.system.areas.projections.services;

import org.movies.system.areas.projections.models.binding.ProjectionBinding;
import org.movies.system.areas.projections.models.binding.ProjectionEditBinding;
import org.movies.system.areas.projections.entities.Projection;
import org.movies.system.areas.projections.models.test.ProjectionTestBinding;
import org.movies.system.areas.projections.models.view.ProjectionViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectionService {

    Long projectionsCount();

    ProjectionTestBinding save(ProjectionBinding projectionBinding);

    void edit(String id, ProjectionEditBinding projectionEditBinding);

    Page<Projection> findAllProjections(Pageable pageable);

    Projection findById(String id);

    ProjectionViewDto findProjectionView(String id);

    Page<Projection> findAllByCinemaName(String cinemaName, Pageable pageable);

    ProjectionEditBinding getEditProjection(String id);

    void delete(String id);

    boolean validProjection(ProjectionBinding projectionBinding, ProjectionEditBinding projectionEditBinding, String editId);
}
