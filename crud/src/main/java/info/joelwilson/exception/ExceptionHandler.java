package info.joelwilson.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNoHandlerFoundException() {

        ModelAndView mv = new ModelAndView("error");

        mv.addObject("errorDesc", "Page Not Found.");
        mv.addObject("title","404 - Page Not Found" );

        return mv;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ModelAndView serverException() {

        ModelAndView mv = new ModelAndView("error");

        mv.addObject("errorDesc", "Error");
        mv.addObject("title","Error" );

        return mv;
    }
}
