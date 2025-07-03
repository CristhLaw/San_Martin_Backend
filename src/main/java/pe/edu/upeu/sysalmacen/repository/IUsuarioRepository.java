package pe.edu.upeu.sysalmacen.repository;

import pe.edu.upeu.sysalmacen.model.Usuario;

import java.util.Optional;

public interface IUsuarioRepository extends ICrudGenericoRepository<Usuario,Long>{
    Optional<Usuario> findOneByUser(String user);
}
