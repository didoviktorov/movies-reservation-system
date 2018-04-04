package org.movies.system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

    @Autowired
    public HomeController() {
    }

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {
        return this.view("index");
    }


}
