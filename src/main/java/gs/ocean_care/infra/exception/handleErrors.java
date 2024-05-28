package gs.ocean_care.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class handleErrors {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException ex){
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(dataErrorValidation::new).toList());
    }

    private record dataErrorValidation(String field, String message){
        public dataErrorValidation(FieldError fieldError){
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}