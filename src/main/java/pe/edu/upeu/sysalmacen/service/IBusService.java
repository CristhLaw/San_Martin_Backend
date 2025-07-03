package pe.edu.upeu.sysalmacen.service;

import pe.edu.upeu.sysalmacen.model.Bus;
import java.util.List;

public interface IBusService extends ICrudGenericoService<Bus, Long> {
    Long maxID();
}
