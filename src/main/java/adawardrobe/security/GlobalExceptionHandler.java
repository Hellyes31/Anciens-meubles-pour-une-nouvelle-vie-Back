package adawardrobe.security;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String msg = ex.getCause() != null ? ex.getCause().getMessage() : "";
        if (msg.contains("users_username_key")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ce nom d'utilisateur est déjà pris !");
        }
        if (msg.contains("users_mail_key")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cet email est déjà utilisé !");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Violation de contrainte en base de données !");
    }
}
