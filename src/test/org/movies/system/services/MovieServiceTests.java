package org.movies.system.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.movies.system.areas.movies.models.binding.MovieBinding;
import org.movies.system.areas.movies.models.test.MovieTestBinding;
import org.movies.system.areas.movies.repositories.MovieRepository;
import org.movies.system.areas.movies.services.MovieServiceImpl;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class MovieServiceTests {

    @Mock
    private MovieRepository movieRepository;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private MovieServiceImpl movieService;

    private MovieBinding testMovie;

    @Before
    public void setUp() {
        this.testMovie = new MovieBinding();

        this.testMovie.setTitle("Test Title");
        this.testMovie.setDescription("Test Description");
        this.testMovie.setDuration(120L);
        this.testMovie.setPosterUrl("Poster");
        this.testMovie.setTrailerUrl("Trailer");

        when(this.movieRepository.save(any()))
                .thenAnswer(a -> a.getArgument(0));
    }

    @Test
    public void createMovie_ShouldNotReturnNull() {
        MovieTestBinding createdEntity = this.movieService.save(this.testMovie);

        Assert.assertNotEquals("Movie entity is null after creation", null, createdEntity);
    }

    @Test
    public void createMovie_WithValidTitle_ShouldParsedCorrectly() {
        MovieTestBinding createdEntity = this.movieService.save(this.testMovie);

        Assert.assertEquals("Movie title is not mapped properly after creation", "Test Title", createdEntity.getTitle());
    }

    @Test
    public void createMovie__WithValidDescription_ShouldParsedCorrectly() {
        MovieTestBinding createdEntity = this.movieService.save(this.testMovie);

        Assert.assertEquals("Movie description is not mapped properly after creation", "Test Description", createdEntity.getDescription());
    }

    @Test
    public void createMovie__WithValidDuration_ShouldParsedCorrectly() {
        MovieTestBinding createdEntity = this.movieService.save(this.testMovie);

        Assert.assertEquals("Movie duration is not mapped properly after creation", 120L, Long.parseLong(createdEntity.getDuration() + ""));
    }

    @Test
    public void createMovie__WithValidPoster_ShouldParsedCorrectly() {
        MovieTestBinding createdEntity = this.movieService.save(this.testMovie);

        Assert.assertEquals("Movie poster is not mapped properly after creation", "Poster", createdEntity.getPosterUrl());
    }

    @Test
    public void createMovie__WithValidTrailer_ShouldParsedCorrectly() {
        MovieTestBinding createdEntity = this.movieService.save(this.testMovie);

        Assert.assertEquals("Movie trailer is not mapped properly after creation", "Trailer", createdEntity.getTrailerUrl());
    }
}
