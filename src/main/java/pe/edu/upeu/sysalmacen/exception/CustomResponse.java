package pe.edu.upeu.sysalmacen.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomResponse {
    private int statusCode;
    private LocalDateTime datetime;
    private String message;
    private String details;
}