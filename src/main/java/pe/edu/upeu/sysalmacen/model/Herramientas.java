package pe.edu.upeu.sysalmacen.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "san_Herramienta")
public class Herramientas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Herramienta")
    private Long idHerramienta;

    @Column(name = "Nombre_Herramienta", nullable = false, length = 100)
    private String nombreHerramienta;

    @Column(name = "Tamaño", length = 255)
    private String Tamaño;

    @Column(name = "Descripción", length = 255)
    private String descripcion;

    @Column(name = "Fecha_Adquisición", nullable = false)
    private LocalDate fechaAdquisicion;

    @Column(name = "Estado", nullable = false, length = 20)
    private String estado;

    @Column(name = "Ubicación", length = 100)
    private String ubicacion;
}
