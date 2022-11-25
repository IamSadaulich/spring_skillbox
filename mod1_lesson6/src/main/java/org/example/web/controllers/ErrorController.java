package org.example.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
public class ErrorController {
    @GetMapping("/404")
    public String notFoundError(Exception ex) {
        return "errors/error_page";
    }
}
