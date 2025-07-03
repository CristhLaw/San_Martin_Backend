package pe.edu.upeu.sysalmacen.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAll(Exception ex) {
        ex.printStackTrace(); // 👈 Mostrar en consola el error exacto

        Map<String, Object> error = new HashMap<>();
        error.put("statusCode", 500);
        error.put("message", ex.getMessage() != null ? ex.getMessage() : "Error interno del servidor");
        error.put("timestamp", LocalDateTime.now());
        return ResponseEntity.status(500).body(error);
    }

}
