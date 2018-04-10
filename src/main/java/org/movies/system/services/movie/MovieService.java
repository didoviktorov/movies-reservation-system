package org.movies.system.services.movie;

import org.movies.system.models.binding.MovieBinding;
import org.movies.system.models.entities.Movie;
import org.movies.system.models.view.MovieViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface MovieService {

    Long moviesCount();

    Movie findById(String id);

    void save(MovieBinding movieBinding);

    void edit(String id, MovieBinding movieBinding);

    List<Movie> findAll();

    Page<Movie> findAllMovies(Pageable pageable);

    MovieViewDto getMovie(String id);

    MovieBinding getEditMovie(String id);

    void delete(String id);
}
