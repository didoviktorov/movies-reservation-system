package org.movies.system.controllers;

import org.movies.system.exceptions.UnauthorizedRequestException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AccessController {

    @GetMapping("/unauthorized")
    public void unauthorized() {
        throw new UnauthorizedRequestException();
    }
}
