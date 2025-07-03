package pe.edu.upeu.sysalmacen.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacen.model.Herramientas;
import pe.edu.upeu.sysalmacen.repository.ICrudGenericoRepository;
import pe.edu.upeu.sysalmacen.repository.IHerramientasRepository;
import pe.edu.upeu.sysalmacen.repository.IRepuestosRepository;
import pe.edu.upeu.sysalmacen.service.ICrudGenericoService;
import pe.edu.upeu.sysalmacen.service.IHerramientasService;

import java.util.List;


@Service
@RequiredArgsConstructor
public class HerramientasServiceImp extends CrudGenericoServiceImp<Herramientas, Long> implements IHerramientasService {

    private final IHerramientasRepository herramientasRepository;

    @Override
    protected ICrudGenericoRepository<Herramientas, Long> getRepo() {
        return herramientasRepository;
    }
}
