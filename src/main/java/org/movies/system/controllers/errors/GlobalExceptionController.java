package org.movies.system.controllers.errors;

import org.movies.system.controllers.BaseController;
import org.movies.system.exceptions.BadRequestException;
import org.movies.system.exceptions.ResourceNotFoundException;
import org.movies.system.exceptions.UnauthorizedRequestException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionController extends BaseController {

    private static final String DEFAULT_ERROR_MESSAGE = "There was an error with your request!";

    @ExceptionHandler(value = { ResourceNotFoundException.class, BadRequestException.class, UnauthorizedRequestException.class })
    public ModelAndView getException(RuntimeException ex) {
        String message = this.getErrorMessage(ex);

        return this.view("error-template", "error", message);
    }

    private String getErrorMessage(Exception ex) {
        String message = "";
        if (ex.getClass().isAnnotationPresent(ResponseStatus.class)) {
            String reason = ex.getClass().getAnnotation(ResponseStatus.class).reason();
            String code = ex.getClass().getAnnotation(ResponseStatus.class).code().toString();
            message += "Status Code: " + code + " Reason: " + reason;
        } else {
            message = DEFAULT_ERROR_MESSAGE;
        }
        return message;
    }
}
