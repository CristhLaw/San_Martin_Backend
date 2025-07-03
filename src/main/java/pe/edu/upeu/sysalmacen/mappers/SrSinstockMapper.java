package pe.edu.upeu.sysalmacen.mappers;

import org.mapstruct.Mapper;
import pe.edu.upeu.sysalmacen.dtos.SrSinstockDTO;
import pe.edu.upeu.sysalmacen.model.SrSinstock;

@Mapper(componentModel = "spring")
public interface SrSinstockMapper extends GenericMapper<SrSinstockDTO, SrSinstock> {
}
