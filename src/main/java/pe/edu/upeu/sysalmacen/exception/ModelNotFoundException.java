package pe.edu.upeu.sysalmacen.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ModelNotFoundException extends RuntimeException{
    private HttpStatus status;
    public ModelNotFoundException(String message) {
        super(message);
    }
    public ModelNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}