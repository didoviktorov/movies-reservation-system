package org.movies.system;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.movies.system.areas.cinemas.entities.Cinema;
import org.movies.system.areas.cinemas.repositories.CinemaRepository;
import org.movies.system.areas.halls.entities.Hall;
import org.movies.system.areas.halls.repositories.HallRepository;
import org.movies.system.areas.movies.entities.Movie;
import org.movies.system.areas.movies.repositories.MovieRepository;
import org.movies.system.areas.projections.models.binding.ProjectionBinding;
import org.movies.system.areas.projections.models.test.ProjectionTestBinding;
import org.movies.system.areas.projections.repositories.ProjectionRepository;
import org.movies.system.areas.projections.services.ProjectionService;
import org.movies.system.areas.projections.services.ProjectionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
@ActiveProfiles("test")
public class ProjectionServiceTests {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private HallRepository hallRepository;

    @Mock
    private ProjectionRepository projectionRepository;

    @Spy
    private ModelMapper modelMapper;

    @InjectMocks
    private ProjectionServiceImpl projectionService;

    private ProjectionBinding testProjection;

    @Before
    public void setUp() {
        Movie movie = new Movie();
        movie.setId("1");
        movie.setTitle("AVATAR");
        this.testEntityManager.persistAndFlush(movie);

        Hall hall = new Hall();
        hall.setName("Hall 1");

        Cinema cinema = new Cinema();
        cinema.setId("1");
        cinema.setName("Varna");
        cinema.getHalls().add(hall);

        this.testEntityManager.persistAndFlush(hall);
        this.testEntityManager.persistAndFlush(cinema);

        this.modelMapper = new ModelMapper();

        this.testProjection = new ProjectionBinding();

        this.testProjection.setProjectionDate(new Date());
        this.testProjection.setCinema("Varna");
        this.testProjection.setHall("Hall 1");
        this.testProjection.setMovie("AVATAR");
        this.testProjection.setHours("10:00 h,13:00 h");

        when(this.projectionRepository.save(any()))
                .thenAnswer(a -> a.getArgument(0));
    }

    @Test
    public void createVirus_WithValidVirus_ShouldNotReturnNull() {
        ProjectionTestBinding createdEntity = this.projectionService.save(this.testProjection);

        Assert.assertNotEquals("Virus entity is null after creation", null, createdEntity);
    }
}
