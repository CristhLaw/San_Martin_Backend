package pe.edu.upeu.sysalmacen.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacen.dtos.SrSinstockDTO;
import pe.edu.upeu.sysalmacen.model.SrSinstock;
import pe.edu.upeu.sysalmacen.repository.ICrudGenericoRepository;
import pe.edu.upeu.sysalmacen.repository.ISrSinstockRepository;
import pe.edu.upeu.sysalmacen.service.ISrSinstockService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SrSinstockServiceImp extends CrudGenericoServiceImp<SrSinstock, Long> implements ISrSinstockService {


    private final ISrSinstockRepository srinstockRepository;


    @Override
    protected ICrudGenericoRepository<SrSinstock, Long> getRepo() {
        return srinstockRepository;
    }

    @Override
    public List<SrSinstock> findAll() {
        return List.of();
    }
}

