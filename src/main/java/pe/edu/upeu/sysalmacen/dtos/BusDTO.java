package pe.edu.upeu.sysalmacen.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BusDTO {
        private Long idbus;
        private String placa;
        private String numeroIdentificador;
        private String modelo;
        private String capacidad;
        private String estado;
        private String fechaAdquisicion;
        private LocalDateTime ultimoMantenimiento;

}
