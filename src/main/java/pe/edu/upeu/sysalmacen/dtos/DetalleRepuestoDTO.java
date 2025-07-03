package pe.edu.upeu.sysalmacen.dtos;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleRepuestoDTO {
    private Long id;
    private Long idRepuesto;
    private String nombreRepuesto;
    private int cantidad;
}

