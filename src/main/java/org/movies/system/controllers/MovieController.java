package org.movies.system.controllers;

import org.movies.system.models.binding.MovieBinding;
import org.movies.system.models.entities.Movie;
import org.movies.system.models.view.MovieSearchViewDto;
import org.movies.system.models.view.MovieViewDto;
import org.movies.system.services.movie.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class MovieController extends BaseController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies/add")
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    public ModelAndView addMovie(@ModelAttribute MovieBinding movieBinding) {
        return this.view("movies/add-movie", "movieBinding", new MovieBinding());
    }

    @PostMapping("/movies/add")
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    public ModelAndView addMovie(@Valid @ModelAttribute MovieBinding movieBinding, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return this.view("movies/add-movie", "movieBinding", movieBinding);
        }

        this.movieService.save(movieBinding);

        return this.redirect("/movies/show");
    }

    @GetMapping("/movies/show")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView allMovies(@PageableDefault(size = 5)Pageable pageable) {
        String[] names = {"movies", "page_count"};

        Page<Movie> movies = this.movieService.findAllMovies(pageable);

        Object[] objects = {movies.getTotalPages() == 0 ? null : movies, this.movieService.moviesCount() / 5 };

        return this.view("movies/all-movies", names, objects);
    }

    @GetMapping("/details/movie/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView detailsMovie(@PathVariable String id) {
        return this.view("movies/movie-details", "movie", this.movieService.getMovie(id));
    }

    @GetMapping("/edit/movie/{id}")
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    public ModelAndView editMovie(@PathVariable String id) {
        String[] names = {"movieBinding", "movieId"};
        Object[] objects = {this.movieService.getEditMovie(id), id};
        return this.view("movies/edit-movie", names, objects);
    }

    @PostMapping("/edit/movie/{id}")
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    public ModelAndView editMovie(@PathVariable String id, @Valid @ModelAttribute MovieBinding movieBinding, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String[] names = {"movieBinding", "movieId"};
            Object[] objects = {movieBinding, id};

            return this.view("movies/edit-movie", names, objects);
        }

        this.movieService.edit(id, movieBinding);

        return this.redirect("/movies/show");
    }

    @GetMapping("/delete/movie/{id}")
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    public ModelAndView deleteVirus(@PathVariable String id) {
        this.movieService.delete(id);

        return this.redirect("/movies/show");
    }
}
