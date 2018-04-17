package org.movies.system.controllers;

import org.movies.system.models.entities.Projection;
import org.movies.system.services.cinema.CinemaService;
import org.movies.system.services.projection.ProjectionService;
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


    @GetMapping("/cinema/{id}")
    @PreAuthorize("isAuthenticated()")
    public ModelAndView allCinemaProjections(@PathVariable String id, @PageableDefault(size = 5)Pageable pageable) {
        String[] names = {"projections", "page_count", "cinema"};

        Page<Projection> projections = this.projectionService.findAllByCinema_Id(id, pageable);

        Object[] objects = {
                projections.getTotalPages() == 0 ? null : projections,
                this.projectionService.projectionsCount() / 5,
                this.cinemaService.findById(id).getName()
        };

        return this.view("projections/all-projections", names, objects);
    }
}
