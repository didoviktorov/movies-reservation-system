package org.movies.system.areas.projections.services;

import org.modelmapper.ModelMapper;
import org.movies.system.areas.projections.models.test.ProjectionTestBinding;
import org.movies.system.exceptions.BadRequestException;
import org.movies.system.areas.projections.models.binding.ProjectionBinding;
import org.movies.system.areas.projections.models.binding.ProjectionEditBinding;
import org.movies.system.areas.projections.entities.Projection;
import org.movies.system.areas.projections.models.view.ProjectionViewDto;
import org.movies.system.areas.projections.repositories.ProjectionRepository;
import org.movies.system.areas.cinemas.services.CinemaService;
import org.movies.system.areas.halls.services.HallService;
import org.movies.system.areas.movies.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProjectionServiceImpl implements ProjectionService {

    private ProjectionRepository projectionRepository;

    private MovieService movieService;

    private CinemaService cinemaService;

    private HallService hallService;

    private ModelMapper modelMapper;

    @Autowired
    public ProjectionServiceImpl(ProjectionRepository projectionRepository, MovieService movieService, CinemaService cinemaService, HallService hallService, ModelMapper modelMapper) {
        this.projectionRepository = projectionRepository;
        this.movieService = movieService;
        this.cinemaService = cinemaService;
        this.hallService = hallService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long projectionsCount() {
        return this.projectionRepository.count();
    }

    @Override
    public ProjectionTestBinding save(ProjectionBinding projectionBinding) {
        Projection projection = this.modelMapper.map(projectionBinding, Projection.class);
        projection.setMovie(this.movieService.findByTitle(projectionBinding.getMovie()));
        projection.setCinema(this.cinemaService.findByName(projectionBinding.getCinema()));
        projection.setHall(this.hallService.findByName(projectionBinding.getHall()));

        Projection projection1 = this.projectionRepository.save(projection);
        ProjectionTestBinding testBinding = this.modelMapper.map(projection1, ProjectionTestBinding.class);
        return testBinding;
    }

    @Override
    public void edit(String id, ProjectionEditBinding projectionEditBinding) {
        Projection projection = this.modelMapper.map(projectionEditBinding, Projection.class);
        projection.setId(id);
        this.projectionRepository.save(projection);
    }

    @Override
    public Page<Projection> findAllProjections(Pageable pageable) {
        return this.projectionRepository.findAllByDeletedOnNullAndMovieDeletedOnNull(pageable);
    }

    @Override
    public Projection findById(String id) {
        Projection projection = this.projectionRepository.findFirstById(id);
        this.checkProjection(projection);
        return projection;
    }

    @Override
    public ProjectionViewDto findProjectionView(String id) {
        return this.modelMapper.map(this.findById(id), ProjectionViewDto.class);
    }

    @Override
    public Page<Projection> findAllByCinemaName(String cinemaName, Pageable pageable) {
        return this.projectionRepository.findAllByCinemaNameAndDeletedOnNullAndMovieDeletedOnNull(cinemaName, pageable);
    }


    @Override
    public ProjectionEditBinding getEditProjection(String id) {
        Projection projection = this.projectionRepository.findFirstById(id);
        return this.modelMapper.map(projection, ProjectionEditBinding.class);
    }

    @Override
    public void delete(String id) {
        Projection projection = this.projectionRepository.findFirstById(id);
        projection.setDeletedOn(new Date());
        this.projectionRepository.save(projection);
    }

    public boolean validProjection(ProjectionBinding projectionBinding, ProjectionEditBinding projectionEditBinding, String editId) {
        List<Projection> allProjections = this.projectionRepository.findAll();
        boolean isSameCinema;
        boolean isSameHall;
        boolean isSameDate;
        for (Projection currentProjection : allProjections) {
            if (projectionBinding != null) {
                isSameCinema = currentProjection.getCinema().getId().equals(projectionBinding.getCinema());
                isSameHall = currentProjection.getHall().getId().equals(projectionBinding.getHall());
                isSameDate = currentProjection.getProjectionDate().compareTo(projectionBinding.getProjectionDate()) == 0;
            } else {
                isSameCinema = currentProjection.getCinema().getId().equals(projectionEditBinding.getCinema().getId());
                isSameHall = currentProjection.getHall().getId().equals(projectionEditBinding.getHall().getId());
                isSameDate = currentProjection.getProjectionDate().compareTo(projectionEditBinding.getProjectionDate()) == 0;
            }

            if (isSameCinema && isSameHall && isSameDate) {
                String[] projectionHours =
                        projectionBinding != null ?
                                projectionBinding.getHours().split(",")
                                : projectionEditBinding.getHours().split(",");
                for (String projectionHour : projectionHours) {
                    if (currentProjection.getHours().contains(projectionHour) && !currentProjection.getId().equals(editId)) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private void checkProjection(Projection projection) {
        if (projection == null) {
            throw new BadRequestException();
        }
    }
}
