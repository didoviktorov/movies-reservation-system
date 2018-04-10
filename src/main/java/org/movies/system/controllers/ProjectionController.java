package org.movies.system.controllers;

import org.movies.system.models.binding.ProjectionBinding;
import org.movies.system.models.binding.ProjectionEditBinding;
import org.movies.system.models.entities.Projection;
import org.movies.system.services.cinema.CinemaService;
import org.movies.system.services.cinema.ProjectionService;
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
public class ProjectionController extends BaseController {

    private ProjectionService projectionService;

    private MovieService movieService;

    @Autowired
    public ProjectionController(ProjectionService projectionService, CinemaService cinemaService, MovieService movieService) {
        this.projectionService = projectionService;
        this.movieService = movieService;
    }

    @GetMapping("/projections/show")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView allMovies(@PageableDefault(size = 5)Pageable pageable) {
        String[] names = {"projections", "page_count"};

        Page<Projection> projections = this.projectionService.findAllProjections(pageable);

        Object[] objects = {projections.getTotalPages() == 0 ? null : projections, this.projectionService.projectionsCount() / 5 };

        return this.view("projections/all-projections", names, objects);
    }

    @GetMapping("/projections/add")
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    public ModelAndView addProjection(@ModelAttribute ProjectionBinding projectionBinding) {
        String[] names = {"projectionBinding", "movies"};

        Object[] objects = { new ProjectionBinding(), this.movieService.findAll() };

        return this.view("projections/add-projection", names, objects);
    }

    @PostMapping("/projections/add")
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    public ModelAndView addProjection(@Valid @ModelAttribute ProjectionBinding projectionBinding, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String[] names = {"projectionBinding", "movies"};

            Object[] objects = { projectionBinding, this.movieService.findAll() };

            return this.view("projections/add-projection", names, objects);
        }

        this.projectionService.save(projectionBinding);

        return this.redirect("/projections/show");
    }

    @GetMapping("/edit/projection/{id}")
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    public ModelAndView editProjection(@PathVariable String id) {
        String[] names = {"projectionEditBinding", "movies", "projectionId" };

        Object[] objects = { this.projectionService.getEditProjection(id), this.movieService.findAll(), id };

        return this.view("projections/edit-projection", names, objects);
    }

    @PostMapping("/edit/projection/{id}")
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    public ModelAndView editProjection(@PathVariable String id, @Valid @ModelAttribute ProjectionEditBinding projectionEditBinding, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String[] names = {"projectionEditBinding", "movies", "projectionId" };

            Object[] objects = { projectionEditBinding, this.movieService.findAll(), id };

            return this.view("projections/edit-projection", names, objects);
        }
        this.projectionService.edit(id, projectionEditBinding);

        return this.redirect("/projections/show");
    }

    @GetMapping("/delete/projection/{id}")
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    public ModelAndView deleteVirus(@PathVariable String id) {
        this.projectionService.delete(id);

        return this.redirect("/projections/show");
    }
}
