package pe.edu.upeu.sysalmacen.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacen.model.Repuestos;
import pe.edu.upeu.sysalmacen.model.Usuario;
import pe.edu.upeu.sysalmacen.repository.ICrudGenericoRepository;
import pe.edu.upeu.sysalmacen.repository.IRepuestosRepository;
import pe.edu.upeu.sysalmacen.service.IRepuestosService;
import pe.edu.upeu.sysalmacen.service.IUsuarioService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepuestosServiceImp extends CrudGenericoServiceImp<Repuestos, Long> implements IRepuestosService {

    private final IRepuestosRepository repuestosRepository;


    @Override
    protected ICrudGenericoRepository<Repuestos, Long> getRepo() {
        return repuestosRepository;
    }
}
