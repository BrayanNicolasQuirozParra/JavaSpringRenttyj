package co.com.usta.renttyj.handler;

import co.com.usta.renttyj.handler.exceptions.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleAllExceptions(Exception ex, Model model) {
        model.addAttribute("error", "Error interno del sistema");
        return "error-template";
    }

    @ExceptionHandler(CustomException.class)
    public String customException(Exception ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error-template";
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public String usernameNotFoundException(Exception ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "error-template";
    }
}
