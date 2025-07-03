package pe.edu.upeu.sysalmacen.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "san_detalle_repuesto")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleRepuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_solicitud", nullable = false)
    private SolicitudRepuesto solicitud;

    @ManyToOne
    @JoinColumn(name = "id_Repuestos", nullable = false)
    private Repuestos repuesto;



    @Column(nullable = false)
    private Integer cantidad;
}

