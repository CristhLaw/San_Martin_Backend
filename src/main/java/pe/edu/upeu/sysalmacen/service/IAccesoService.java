package pe.edu.upeu.sysalmacen.service;

import pe.edu.upeu.sysalmacen.model.Acceso;

import java.util.List;

public interface IAccesoService {
    List<Acceso> getAccesoByUser(String username);
}
