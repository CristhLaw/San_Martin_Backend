package pe.edu.upeu.sysalmacen.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacen.model.Acceso;
import pe.edu.upeu.sysalmacen.repository.IAccesoRepository;
import pe.edu.upeu.sysalmacen.repository.ICrudGenericoRepository;
import pe.edu.upeu.sysalmacen.service.IAccesoService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccesoServiceImp extends CrudGenericoServiceImp<Acceso, Long> implements IAccesoService {
    private final IAccesoRepository repo;

    @Override
    public List<Acceso> getAccesoByUser(String username) {
        return List.of();
    }

    @Override
    protected ICrudGenericoRepository<Acceso, Long> getRepo() {
        return repo;
    }

    @Override
    public List<Acceso> findAll() {
        return List.of();
    }
}
