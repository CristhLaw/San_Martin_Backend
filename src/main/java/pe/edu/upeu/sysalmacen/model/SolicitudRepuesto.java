package pe.edu.upeu.sysalmacen.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "san_solicitud_repuestos")
public class SolicitudRepuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Long idSolicitud;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false,
            foreignKey = @ForeignKey(name = "FK_usuario_solicitud"))
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_bus", nullable = false,
            foreignKey = @ForeignKey(name = "FK_bus_solicitud"))
    private Bus bus;

    @Column(name = "descripcion_de_falla", length = 255)
    private String descripcionDeFalla;

    @Column(name = "estado", length = 50)
    private String estado;

    @Column(name = "fecha_registro", updatable = false)
    private LocalDateTime fechaRegistro;

    @Column(name = "observacion_revision", columnDefinition = "TEXT")
    private String observacionRevision;

    // 🔧 Repuestos solicitados (detalle)
    @OneToMany(mappedBy = "solicitud", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleRepuesto> detalleRepuestos;

    // 🛠️ Herramientas solicitadas (detalle)
    @OneToMany(mappedBy = "solicitud", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleHerramienta> detalleHerramientas;
}
//completo sin diseño