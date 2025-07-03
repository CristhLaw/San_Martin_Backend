package pe.edu.upeu.sysalmacen.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.edu.upeu.sysalmacen.dtos.DetalleRepuestoDTO;
import pe.edu.upeu.sysalmacen.model.DetalleRepuesto;

@Mapper(componentModel = "spring")
public interface DetalleRepuestoMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "repuesto.idRepuestos", target = "idRepuesto") // ← corregido
    @Mapping(source = "repuesto.nombreRepuesto", target = "nombreRepuesto")
    DetalleRepuestoDTO toDTO(DetalleRepuesto entity);
}
