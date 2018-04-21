package org.movies.system.areas.cinemas.controllers;

import org.movies.system.controllers.BaseController;
import org.movies.system.areas.projections.entities.Projection;
import org.movies.system.areas.cinemas.services.CinemaService;
import org.movies.system.areas.projections.services.ProjectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CinemaController extends BaseController {

    private ProjectionService projectionService;

    private CinemaService cinemaService;

    @Autowired
    public CinemaController(CinemaService cinemaService, ProjectionService projectionService, CinemaService cinemaService1) {
        this.projectionService = projectionService;
        this.cinemaService = cinemaService1;
    }

    @GetMapping("/cinema/{name}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView allCinemaProjections(@PathVariable String name, @PageableDefault(size = 5)Pageable pageable) {
        String[] names = {"projections", "page_count", "cinema"};

        Page<Projection> projections = this.projectionService.findAllByCinemaName(name, pageable);

        Object[] objects = {
                projections.getTotalPages() == 0 ? null : projections,
                this.projectionService.projectionsCount() / 5,
                this.cinemaService.findByName(name).getName()
        };

        return this.view("projections/all-projections", names, objects);
    }
}
