package pe.edu.upeu.sysalmacen.mappers;

import org.mapstruct.*;
import pe.edu.upeu.sysalmacen.dtos.*;
import pe.edu.upeu.sysalmacen.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {
        UsuarioMapper.class,
        BusMapper.class,
        RepuestosMapper.class,
        HerramientasMapper.class,
        DetalleRepuestoMapper.class,
        DetalleHerramientaMapper.class
})
public interface SolicitudRepuestoMapper extends GenericMapper<SolicitudRepuestoDTO, SolicitudRepuesto> {

    @Mappings({
            @Mapping(source = "usuario", target = "usuario"),
            @Mapping(source = "bus", target = "bus"),
            @Mapping(source = "detalleRepuestos", target = "detalleRepuestos"),
            @Mapping(source = "detalleHerramientas", target = "detalleHerramientas")
    })
    SolicitudRepuestoDTO toDTO(SolicitudRepuesto entidad);

    @Mappings({
            @Mapping(target = "usuario", ignore = true),
            @Mapping(target = "bus", ignore = true),
            @Mapping(target = "detalleRepuestos", ignore = true),
            @Mapping(target = "detalleHerramientas", ignore = true)
    })
    SolicitudRepuesto toEntityFromCADTO(SolicitudRepuestoDTO.SolicitudRepuestoCADto dto);

    // ✅ Métodos seguros para convertir listas a mutables
    default List<DetalleRepuesto> toMutableDetalleRepuestos(List<DetalleRepuesto> detalleList) {
        return detalleList == null ? new ArrayList<>() : new ArrayList<>(detalleList);
    }

    default List<DetalleHerramienta> toMutableDetalleHerramientas(List<DetalleHerramienta> detalleList) {
        return detalleList == null ? new ArrayList<>() : new ArrayList<>(detalleList);
    }
}
