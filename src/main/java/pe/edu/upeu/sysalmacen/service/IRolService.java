package pe.edu.upeu.sysalmacen.service;

import pe.edu.upeu.sysalmacen.model.Rol;

import java.util.Optional;

public interface IRolService extends ICrudGenericoService<Rol, Long>{
    public Optional<Rol> getByNombre(Rol.RolNombre rolNombre);
}