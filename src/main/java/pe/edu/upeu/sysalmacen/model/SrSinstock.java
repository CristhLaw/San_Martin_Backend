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
@Table(name = "san_sr_sinstock")
public class SrSinstock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sinstock")
    private Long idSinstock;

    @Column(name = "nombre_repuesto", nullable = false, length = 100)
    private String nombreRepuesto;

    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "marca", nullable = false, length = 50)
    private String marca;

    @Column(name = "codigo_fabricante", length = 50)
    private String codigoFabricante;

    @Column(name = "unidad_medida", nullable = false, length = 20)
    private String unidadMedida;
}
