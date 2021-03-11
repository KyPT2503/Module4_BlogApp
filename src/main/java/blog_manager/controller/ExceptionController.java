package blog_manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView showErrPage() {
        return new ModelAndView("error-404");
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView showErrorPage() {
        return new ModelAndView("error-404");
    }
}
