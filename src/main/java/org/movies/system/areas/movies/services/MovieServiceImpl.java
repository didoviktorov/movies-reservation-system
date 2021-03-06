package org.movies.system.areas.movies.services;

import org.modelmapper.ModelMapper;
import org.movies.system.areas.movies.models.test.MovieTestBinding;
import org.movies.system.exceptions.BadRequestException;
import org.movies.system.areas.movies.models.binding.MovieBinding;
import org.movies.system.areas.movies.entities.Movie;
import org.movies.system.areas.movies.models.view.MovieViewDto;
import org.movies.system.areas.movies.repositories.MovieRepository;
import org.movies.system.utils.EscapeCharacters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

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
    public Movie findById(String id) {
        Movie movie = this.movieRepository.findFirstById(id);
        this.checkMovie(movie);
        return movie;
    }

    @Override
    public Movie findByTitle(String title) {
        return this.movieRepository.findFirstByTitle(title);
    }

    @Override
    public MovieTestBinding save(MovieBinding movieBinding) {
        Movie movie = this.modelMapper.map(movieBinding, Movie.class);
        movie.setTrailerUrl(getEmbedCode(movie.getTrailerUrl()));
        Movie movie1 = this.movieRepository.save(movie);
        return this.modelMapper.map(movie1, MovieTestBinding.class);
    }

    @Override
    public void edit(String id, MovieBinding movieBinding) {
        Movie movie = this.modelMapper.map(movieBinding, Movie.class);
        movie.setId(id);
        movie.setTrailerUrl(getEmbedCode(movie.getTrailerUrl()));
        this.movieRepository.save(movie);
    }

    @Override
    public List<Movie> findAll() {
        return this.movieRepository.findAllByDeletedOnNull();
    }

    @Override
    public Page<Movie> findAllMovies(Pageable pageable) {
        return this.movieRepository.findAllByDeletedOnNull(pageable);
    }

    @Override
    public Page<Movie> findAllByTitle(String title, Pageable pageable) {
        return this.movieRepository.findAllByTitleContainingAndDeletedOnNull(title, pageable);
    }

    @Override
    public MovieViewDto getMovie(String id) {
        Movie movie = this.movieRepository.findFirstById(id);
        this.checkMovie(movie);
        return this.modelMapper.map(movie, MovieViewDto.class);
    }

    @Override
    public MovieBinding getEditMovie(String id) {
        Movie movie = this.movieRepository.findFirstById(id);
        this.checkMovie(movie);
        return this.modelMapper.map(movie, MovieBinding.class);
    }

    @Override
    public void delete(String id) {
        Movie movie = this.movieRepository.getOne(id);
        movie.setDeletedOn(new Date());

        this.movieRepository.save(movie);
    }

    private String getEmbedCode(String link) {
        link = EscapeCharacters.escape(link);
        link = link.substring(link.lastIndexOf("=") + 1);
        return link;
    }

    private void checkMovie(Movie movie) {
        if (movie == null) {
            throw new BadRequestException();
        }
    }
}
