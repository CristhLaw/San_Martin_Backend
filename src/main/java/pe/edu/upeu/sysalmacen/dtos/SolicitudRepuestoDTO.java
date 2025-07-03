package pe.edu.upeu.sysalmacen.dtos;

import lombok.*;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class SolicitudRepuestoDTO {
    private Long idSolicitud;
    private String descripcionDeFalla;
    private String estado;
    private UsuarioDTO usuario;
    private BusDTO bus;

    private List<DetalleRepuestoDTO> detalleRepuestos;
    private List<DetalleHerramientaDTO> detalleHerramientas;

    public record SolicitudRepuestoCADto(
            String descripcionDeFalla,
            String estado,
            Long usuario,
            Long bus,
            List<RepuestoCantidad> repuestos,
            List<HerramientaCantidad> herramientas
    ) {}

    public record RepuestoCantidad(Long idRepuesto, int cantidad) {}
    public record HerramientaCantidad(Long idHerramienta, int cantidad) {}
}

