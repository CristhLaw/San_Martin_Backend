package pe.edu.upeu.sysalmacen.mappers;

import org.mapstruct.Mapper;
import pe.edu.upeu.sysalmacen.dtos.AccesoDTO;
import pe.edu.upeu.sysalmacen.model.Acceso;

@Mapper(componentModel = "spring")
public interface AccesoMapper extends GenericMapper<AccesoDTO, Acceso> {
}