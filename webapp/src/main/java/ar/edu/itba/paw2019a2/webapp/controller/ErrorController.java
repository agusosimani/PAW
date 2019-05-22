package ar.edu.itba.paw2019a2.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

    @RequestMapping(value = "/400", method = {RequestMethod.GET})
    public ModelAndView badRequest() {
        return new ModelAndView("400");
    }

    @RequestMapping(value = "/403", method = {RequestMethod.GET})
    public ModelAndView forbidden() {
        return new ModelAndView("403");
    }

    @RequestMapping(value = "/404", method = {RequestMethod.GET})
    public ModelAndView notFound() {
        return new ModelAndView("404");
    }

    @RequestMapping(value = "/error", method = {RequestMethod.GET})
    public ModelAndView serverError() {
        return new ModelAndView("error");
    }
}
