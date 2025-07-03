package pe.edu.upeu.sysalmacen.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "upeu_roles")
public class Rol {

    public enum RolNombre { ADMIN, USER, DBA, Chofer}

    @Id
    @Column(name = "id_rol")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRol;

    @Column(name = "nombre", nullable = false, length = 60)
    @Enumerated(EnumType.STRING)
    private RolNombre nombre;

    @Column(name = "descripcion", nullable = false, length = 120)
    private String descripcion;
}