package pe.edu.upeu.sysalmacen.mappers;

import org.mapstruct.Mapper;
import pe.edu.upeu.sysalmacen.dtos.HerramientasDTO;
import pe.edu.upeu.sysalmacen.model.Herramientas;



@Mapper(componentModel = "spring")
public interface HerramientasMapper extends GenericMapper<HerramientasDTO, Herramientas> {
}
