package org.movies.system.controllers;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {

    protected ModelAndView view(String view) {
        ModelAndView modelAndView = new ModelAndView(view);

        return modelAndView;
    }

    protected ModelAndView view(String view, String name, Object model) {
        ModelAndView modelAndView = new ModelAndView(view);

        modelAndView.addObject(name, model);

        return modelAndView;
    }

    protected ModelAndView view(String view, String[] names, Object[] models) {
        ModelAndView modelAndView = new ModelAndView(view);

        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            Object model = models[i];
            modelAndView.addObject(name, model);
        }

        return modelAndView;
    }

    protected ModelAndView redirect(String url) {
        return new ModelAndView("redirect:" + url);
    }
}
