package pe.edu.upeu.sysalmacen.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upeu.sysalmacen.exception.CustomResponse;
import pe.edu.upeu.sysalmacen.model.Bus;
import pe.edu.upeu.sysalmacen.repository.IBusRepository;
import pe.edu.upeu.sysalmacen.repository.ICrudGenericoRepository;
import pe.edu.upeu.sysalmacen.service.IBusService;

import java.time.LocalDateTime;
import java.util.List;



@Transactional
@Service("busServiceA")
@RequiredArgsConstructor
public class BusServiceImp extends CrudGenericoServiceImp<Bus, Long> implements IBusService {

    private final IBusRepository busRepository;


    @Override
    public Long maxID() {
        return 0L;
    }

    @Override
    protected ICrudGenericoRepository<Bus, Long> getRepo() {
        return busRepository;
    }
}












