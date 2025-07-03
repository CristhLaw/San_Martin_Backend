package pe.edu.upeu.sysalmacen.mappers;

import org.mapstruct.Mapper;
import pe.edu.upeu.sysalmacen.dtos.BusDTO;
import pe.edu.upeu.sysalmacen.model.Bus;

@Mapper(componentModel = "spring")
public interface BusMapper extends GenericMapper<BusDTO, Bus> {

}
