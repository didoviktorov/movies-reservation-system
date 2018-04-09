package org.movies.system.services.movie;

import org.modelmapper.ModelMapper;
import org.movies.system.models.binding.MovieBinding;
import org.movies.system.models.entities.Movie;
import org.movies.system.models.view.MovieViewDto;
import org.movies.system.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    private ModelMapper modelMapper;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long moviesCount() {
        return this.movieRepository.count();
    }

    @Override
    public void save(MovieBinding movieBinding) {
        Movie movie = this.modelMapper.map(movieBinding, Movie.class);
        this.movieRepository.save(movie);
    }

    @Override
    public void edit(String id, MovieBinding movieBinding) {
        Movie movie = this.modelMapper.map(movieBinding, Movie.class);
        movie.setId(id);

        this.movieRepository.save(movie);
    }

    @Override
    public Page<Movie> findAllMovies(Pageable pageable) {
        return this.movieRepository.findAll(pageable);
    }

    @Override
    public MovieViewDto getMovie(String id) {
        Movie movie = this.movieRepository.findFirstById(id);

        MovieViewDto movieViewDto = this.modelMapper.map(movie, MovieViewDto.class);

        return movieViewDto;
    }

    @Override
    public MovieBinding getEditMovie(String id) {
        MovieBinding movieBinding = this.modelMapper.map(this.movieRepository.findFirstById(id), MovieBinding.class);
        return movieBinding;
    }

    @Override
    public void delete(String id) {
        this.movieRepository.delete(this.movieRepository.getOne(id));
    }
}
