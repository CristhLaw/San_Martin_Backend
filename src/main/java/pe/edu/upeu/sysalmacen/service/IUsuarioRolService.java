package pe.edu.upeu.sysalmacen.service;

import pe.edu.upeu.sysalmacen.model.UsuarioRol;

import java.util.List;

public interface IUsuarioRolService {
    List<UsuarioRol> findOneByUsuarioUser(String user);
    UsuarioRol save(UsuarioRol ur);
    //Usuario login(String usuario, String password);

}