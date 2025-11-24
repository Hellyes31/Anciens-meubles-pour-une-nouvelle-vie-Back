package adawardrobe.security;

import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 1️⃣ Validation Bean Validation (regex, @NotNull, etc.)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationErrors(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getDefaultMessage())
                .findFirst()
                .orElse("Erreur de validation");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        Throwable cause = ex.getCause();
        while (cause != null && !(cause instanceof org.hibernate.exception.ConstraintViolationException)) {
            cause = cause.getCause();
        }

        if (cause instanceof org.hibernate.exception.ConstraintViolationException cve) {
            String constraintName = cve.getConstraintName();
            if ("users_username_unique".equalsIgnoreCase(constraintName)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Ce nom d'utilisateur est déjà pris !");
            }
            if ("users_mail_unique".equalsIgnoreCase(constraintName)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Cet email est déjà utilisé !");
            }
            if ("chk_email_format".equalsIgnoreCase(constraintName)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Le format de l'email est invalide !");
            }
        }

        // fallback si on ne peut pas détecter la contrainte
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Violation de contrainte en base de données !");
    }


    // 3️⃣ Cas rare : contraintes JPA/BeanValidation spécifiques
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException ex) {
        String errorMessage = ex.getConstraintViolations()
                .stream()
                .map(cv -> cv.getMessage())
                .findFirst()
                .orElse("Violation de contrainte");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    // 4️⃣ Erreurs inattendues
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAll(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Une erreur inattendue est survenue : " + ex.getMessage());
    }
}

