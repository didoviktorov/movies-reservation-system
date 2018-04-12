package org.movies.system.services.cinema;

import org.modelmapper.ModelMapper;
import org.movies.system.models.binding.ProjectionBinding;
import org.movies.system.models.binding.ProjectionEditBinding;
import org.movies.system.models.entities.Projection;
import org.movies.system.repositories.ProjectionRepository;
import org.movies.system.services.hall.HallService;
import org.movies.system.services.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

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
    public void save(ProjectionBinding projectionBinding) {
        Projection projection = this.modelMapper.map(projectionBinding, Projection.class);
        projection.setMovie(this.movieService.findById(projectionBinding.getMovie()));
        projection.setCinema(this.cinemaService.findById(projectionBinding.getCinema()));
        projection.setHall(this.hallService.findById(projectionBinding.getHall()));

        this.projectionRepository.save(projection);
    }

    @Override
    public void edit(String id, ProjectionEditBinding projectionEditBinding) {
        Projection projection = this.modelMapper.map(projectionEditBinding, Projection.class);
        projection.setId(id);
        this.projectionRepository.save(projection);
    }

    @Override
    public Page<Projection> findAllProjections(Pageable pageable) {
        return this.projectionRepository.findAll(pageable);
    }

    @Override
    public Page<Projection> findAllByCinema_Id(String cinema_id, Pageable pageable) {
        return this.projectionRepository.findAllByCinema_Id(cinema_id, pageable);
    }

    @Override
    public ProjectionEditBinding getEditProjection(String id) {
        Projection projection = this.projectionRepository.findFirstById(id);
        ProjectionEditBinding projectionEditBinding = this.modelMapper.map(projection, ProjectionEditBinding.class);
        return projectionEditBinding;
    }

    @Override
    public void delete(String id) {
        this.projectionRepository.delete(this.projectionRepository.findFirstById(id));
    }
}
