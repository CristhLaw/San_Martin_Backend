package pe.edu.upeu.sysalmacen.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.sysalmacen.model.Categoria;
import pe.edu.upeu.sysalmacen.repository.ICategoriaRepository;
import pe.edu.upeu.sysalmacen.repository.ICrudGenericoRepository;
import pe.edu.upeu.sysalmacen.service.ICategoriaService;

import java.util.List;


@Transactional
@Service
@RequiredArgsConstructor
public class CategoriaServiceImp extends CrudGenericoServiceImp<Categoria,Long> implements ICategoriaService {

    private final ICategoriaRepository categoriaRepository;


    @Override
    protected ICrudGenericoRepository<Categoria, Long> getRepo() {
        return categoriaRepository;
    }

    @Override
    public List<Categoria> findAll() {
        return List.of();
    }
}
