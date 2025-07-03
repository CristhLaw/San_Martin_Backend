package pe.edu.upeu.sysalmacen.dtos;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RepuestosDTO {
    private Long idRepuestos;
    private String nombreRepuesto;
    private String descripcion;
    private Integer cantidad;
    private String marca;
    private String codigoFabricante;
    private String unidadMedida;
}
