package pe.edu.upeu.sysalmacen.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.sysalmacen.dtos.DetalleHerramientaDTO;
import pe.edu.upeu.sysalmacen.model.DetalleHerramienta;

@Mapper(componentModel = "spring")
public interface DetalleHerramientaMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "herramienta.idHerramientas", target = "idHerramienta") // ← corregido
    @Mapping(source = "herramienta.nombreHerramienta", target = "nombreHerramienta")
    DetalleHerramientaDTO toDTO(DetalleHerramienta entity);
}
