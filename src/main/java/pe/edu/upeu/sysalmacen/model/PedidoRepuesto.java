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
@Table(name = "pedido_repuesto")
public class PedidoRepuesto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido_repuesto")
    private Long idPedidoRepuesto;

    @Column(name = "nombre_repuesto", nullable = false, length = 100)
    private String nombreRepuesto;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

    @Column(name = "unidad_medida", nullable = false, length = 50)
    private String unidadMedida;

    @Column(name = "marca", length = 100)
    private String marca;

    @Column(name = "codigo_fabricante", length = 100)
    private String codigoFabricante;
}
