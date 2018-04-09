package org.movies.system.controllers;

import org.movies.system.services.cinema.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

    private CinemaService cinemaService;

    @Autowired
    public HomeController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {

        return this.view("index", "cinemas", this.cinemaService.findAll());
    }


}
