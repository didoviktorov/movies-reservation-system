package org.movies.system.controllers;

import org.movies.system.services.CityService;
import org.movies.system.services.RoleService;
import org.movies.system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {

    private CityService cityService;
    private RoleService roleService;
    private UserService userService;

    @Autowired
    public HomeController(CityService cityService, RoleService roleService, UserService userService) {
        this.cityService = cityService;
        this.roleService = roleService;
        this.userService = userService;

        this.seedInfo();
    }

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView) {
        return this.view("index");
    }

    private void seedInfo() {

        if (this.roleService.roleCount() == 0) {
            this.roleService.seedRoles();
        }

        if (this.userService.userCount() == 0) {
            this.userService.seedUser();
        }

        if (this.cityService.cityCount() == 0) {
            this.cityService.seedCities();
        }
    }
}
