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
@Table(name = "san_Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Usuario")
    private Long idUsuario;

    @Column(name = "Nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "Correo_Electronico", nullable = false, unique = true, length = 100)
    private String correoElectronico;

    @Column(name = "Teléfono", length = 20)
    private String telefono;

    @Column(name = "Dirección", length = 150)
    private String direccion;

    @Column(name = "Fecha_Registro", nullable = false)
    private LocalDate fechaRegistro;

    @Column(name = "Rol", nullable = false, length = 50)
    private String rol;
}
