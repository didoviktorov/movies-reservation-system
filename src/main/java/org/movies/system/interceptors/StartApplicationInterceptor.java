package org.movies.system.interceptors;

import org.movies.system.services.cinema.CinemaService;
import org.movies.system.services.role.RoleService;
import org.movies.system.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class StartApplicationInterceptor extends HandlerInterceptorAdapter {

    private CinemaService cinemaService;
    private RoleService roleService;
    private UserService userService;

    @Autowired
    public StartApplicationInterceptor(CinemaService cinemaService, RoleService roleService, UserService userService) {
        this.cinemaService = cinemaService;
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        this.seedInfo();
    }

    private void seedInfo() {
        if (this.roleService.roleCount() == 0) {
            this.roleService.seedRoles();
        }

        if (this.userService.userCount() == 0) {
            this.userService.seedUser();
        }

        if (this.cinemaService.cinemaCount() == 0) {
            this.cinemaService.seedCinemas();
        }
    }
}
