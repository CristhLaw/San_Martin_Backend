package pe.edu.upeu.sysalmacen.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class SrSinstockDTO {

    private Long idSinstock;
    private LocalDate fechaSolicitud;
    private String nombreRepuesto;
    private Integer cantidadSolicitada;
    private String estado;
    private String observaciones;
    private Long idUsuario;
}
