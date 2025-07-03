package pe.edu.upeu.sysalmacen.repository;

import pe.edu.upeu.sysalmacen.model.Rol;

import java.util.Optional;

public interface IRolRepository extends ICrudGenericoRepository<Rol, Long>{
    Optional<Rol> findByNombre(Rol.RolNombre rolNombre);

    Optional<Rol> findByDescripcion(String nombre);

}
