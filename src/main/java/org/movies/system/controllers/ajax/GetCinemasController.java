package org.movies.system.controllers.ajax;

import org.movies.system.models.entities.Cinema;
import org.movies.system.services.cinema.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetCinemasController {

    private CinemaService cinemaService;

    @Autowired
    public GetCinemasController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @ResponseBody
    @RequestMapping(value = "/all-cinemas")
    public List<Cinema> getCinemasViaAjax() {

        return this.cinemaService.findAll();
    }

    @ResponseBody
    @RequestMapping(value = "/cinema_ajax/{id}")
    public Cinema getCinemasViaAjax(@PathVariable String id) {
        return this.cinemaService.findById(id);
    }
}
