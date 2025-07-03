package pe.edu.upeu.sysalmacen.dtos;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class HerramientasDTO {
    private Long idHerramientas;
    private String nombreHerramienta;
    private String tamano; // corregido
    private String descripcion;
    private LocalDate fechaAdquisicion;
    private String estado;
    private String ubicacion;
}
