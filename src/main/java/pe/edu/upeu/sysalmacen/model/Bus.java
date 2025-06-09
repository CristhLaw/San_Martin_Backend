package pe.edu.upeu.sysalmacen.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "san_bus") // <-- cámbialo si el nombre real de tu tabla es diferente
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bus")
    private Long idbus;

    @Column(name = "numero_id", nullable = false, length = 100)
    private String numero_id;

    @Column(name = "placa", nullable = false, length = 100)
    private String placa;

    @Column(name = "modelo", nullable = false, length = 100)
    private String modelo;

    @Column(name = "capacidad", nullable = false, length = 100)
    private String capacidad;

    @Column(name = "estado", nullable = false, length = 100)
    private String estado;

    @Column(name = "fecha_adquisicion", nullable = false, length = 100)
    private String fechaAdquisicion;

    @Column(name = "ultimo_mantenimiento", nullable = false)
    private LocalDateTime ultimoMantenimiento;
}
