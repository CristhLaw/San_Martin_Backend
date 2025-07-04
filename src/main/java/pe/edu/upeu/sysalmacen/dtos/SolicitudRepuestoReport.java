package pe.edu.upeu.sysalmacen.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SolicitudRepuestoReport {
    private Long idSolicitud;
    private String descripcionDeFalla;
    private String estado;
    private LocalDateTime fechaRegistro;
    private String observacionRevision;

    private UsuarioDTO usuario;
    private BusDTO bus;

    private List<DetalleRepuestoDTO> detalleRepuestos;
    private List<DetalleHerramientaDTO> detalleHerramientas;
}
