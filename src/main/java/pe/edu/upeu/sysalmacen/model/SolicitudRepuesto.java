package pe.edu.upeu.sysalmacen.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "san_Solicitud_Repuestos")
public class SolicitudRepuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Solicitud")
    private Long idSolicitud;

    // Clave foránea a Usuario
    @ManyToOne
    @JoinColumn(name = "ID_Usuario", nullable = false)
    private Usuario usuario;

    // Clave foránea a Bus
    @ManyToOne
    @JoinColumn(name = "ID_Bus", nullable = false)
    private Bus bus;

    @Column(name = "Nombre_Repuesto", nullable = false, length = 100)
    private String nombreRepuesto;

    @Column(name = "Descripción_Repuesto", length = 255)
    private String descripcionRepuesto;

    @Column(name = "Cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "Unidad_Medida", nullable = false, length = 50)
    private String unidadMedida;

    @Column(name = "Marca", length = 100)
    private String marca;

    @Column(name = "Codigo_Fabricante", length = 100)
    private String codigoFabricante;

    @Column(name = "Herramientas_Nombre", length = 100)
    private String herramientasNombre;

    @Column(name = "Tamaño_Descripcion", length = 100)
    private String tamañoDescripcion;

    @Column(name = "Descripción_De_Falla", length = 255)
    private String descripcionDeFalla;
}
