package org.movies.system.services.movie;

import org.movies.system.models.binding.MovieBinding;
import org.movies.system.models.entities.Movie;
import org.movies.system.models.view.MovieViewDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MovieService {

    Long moviesCount();

    void save(MovieBinding movieBinding);

    void edit(String id, MovieBinding movieBinding);

    Page<Movie> findAllMovies(Pageable pageable);

    MovieViewDto getMovie(String id);

    MovieBinding getEditMovie(String id);

    void delete(String id);
}
