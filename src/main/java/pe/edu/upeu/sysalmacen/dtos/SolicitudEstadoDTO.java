package pe.edu.upeu.sysalmacen.dtos;

import lombok.Data;

@Data
public class SolicitudEstadoDTO {
    private Long idSolicitud;
    private String estado;
    private String observacionRevision; // ← aquí va el motivo del rechazo
}
