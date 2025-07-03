package pe.edu.upeu.sysalmacen.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "san_herramientas") // mejor en plural y con snake_case
public class Herramientas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_herramientas")
    private Long idHerramientas;

    @Column(name = "nombre_herramienta", nullable = false, length = 100)
    private String nombreHerramienta;

    @Column(name = "tamano", length = 255)
    private String tamano;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "fecha_adquisicion", nullable = false)
    private LocalDate fechaAdquisicion;

    @Column(name = "estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "ubicacion", length = 100)
    private String ubicacion;
}
