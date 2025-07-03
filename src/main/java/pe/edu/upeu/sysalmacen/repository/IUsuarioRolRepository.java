package pe.edu.upeu.sysalmacen.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upeu.sysalmacen.model.Usuario;
import pe.edu.upeu.sysalmacen.model.UsuarioRol;
import pe.edu.upeu.sysalmacen.model.UsuarioRolPK;

import java.util.List;

public interface IUsuarioRolRepository extends ICrudGenericoRepository<UsuarioRol, UsuarioRolPK>{
    @Query("SELECT ur FROM UsuarioRol ur WHERE ur.usuario.user = :user")//Consulta JPQL
    List<UsuarioRol> findOneByUsuarioUser(@Param("user") String user);

    @Query("""
    SELECT ur.usuario FROM UsuarioRol ur
    WHERE ur.usuario.user = :user and ur.usuario.clave=:clave
    """)
    Usuario login(@Param("user") String user, @Param("clave") String clave);

    @Query(value = """
    SELECT u.* FROM upeu_usuario_rol ur inner join upeu_usuario u
    on (ur.usuario_id=u.id_usuario) where u.user=:username and u.clave=:clave
    """, nativeQuery = true)
    Usuario loginOpt(@Param("username") String username, @Param("clave") String clave);
}