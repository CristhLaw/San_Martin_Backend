package pe.edu.upeu.sysalmacen.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleHerramientaDTO {
    private Long id;
    private Long idHerramienta;
    private String nombreHerramienta;
    private int cantidad;
}
