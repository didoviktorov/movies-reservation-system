package org.movies.system.controllers.errors;

import org.movies.system.exceptions.ResourceNotFoundException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorExceptionController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH)
    public void notFound() {
        throw new ResourceNotFoundException();
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
