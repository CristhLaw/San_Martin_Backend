package pe.edu.upeu.sysalmacen.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "upeu_usuario_rol")
@IdClass(UsuarioRolPK.class)
public class UsuarioRol {
    @Id
    private Usuario usuario;
    @Id
    private Rol rol;
}