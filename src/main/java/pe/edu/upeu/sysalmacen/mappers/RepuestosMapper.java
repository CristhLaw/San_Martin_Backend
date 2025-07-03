package pe.edu.upeu.sysalmacen.mappers;

import org.mapstruct.Mapper;
import pe.edu.upeu.sysalmacen.dtos.RepuestosDTO;
import pe.edu.upeu.sysalmacen.model.Repuestos;

@Mapper(componentModel = "spring")
public interface RepuestosMapper extends GenericMapper<RepuestosDTO, Repuestos> {
}
