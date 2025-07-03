package pe.edu.upeu.sysalmacen.mappers;

import org.mapstruct.Mapper;
import pe.edu.upeu.sysalmacen.dtos.RolDTO;
import pe.edu.upeu.sysalmacen.model.Rol;


@Mapper(componentModel = "spring")
public interface RolMapper extends GenericMapper<RolDTO, Rol> {
}
