package pe.edu.upeu.sysalmacen.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacen.dtos.UsuarioDTO;
import pe.edu.upeu.sysalmacen.exception.ModelNotFoundException;
import pe.edu.upeu.sysalmacen.mappers.UsuarioMapper;
import pe.edu.upeu.sysalmacen.model.Rol;
import pe.edu.upeu.sysalmacen.model.Usuario;
import pe.edu.upeu.sysalmacen.model.UsuarioRol;
import pe.edu.upeu.sysalmacen.repository.IUsuarioRepository;
import pe.edu.upeu.sysalmacen.repository.ICrudGenericoRepository;
import pe.edu.upeu.sysalmacen.service.IRolService;
import pe.edu.upeu.sysalmacen.service.IUsuarioRolService;
import pe.edu.upeu.sysalmacen.service.IUsuarioService;

import java.nio.CharBuffer;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl extends CrudGenericoServiceImp<Usuario, Long> implements IUsuarioService {


    private final IUsuarioRepository repo;

    private final IRolService rolService;
    private final IUsuarioRolService iurService;

    //private final PasswordEncoder passwordEncoder; //descomenta al trabajar spring security
    private final UsuarioMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    protected ICrudGenericoRepository<Usuario, Long> getRepo() {
        return repo;
    }



    @Override
    public UsuarioDTO login(UsuarioDTO.CredencialesDto credentialsDto) {
        Usuario user = repo.findOneByUser(credentialsDto.user()).orElseThrow(() -> new ModelNotFoundException("Unknown user", HttpStatus.NOT_FOUND));
        //descomenta al trabajar spring security
        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.clave()), user.getClave())) {
            return userMapper.toDTO(user);
        }

        if (credentialsDto.clave().equals(user.getClave())) {
            return userMapper.toDTO(user);
        }

        throw new ModelNotFoundException("Invalid password", HttpStatus.BAD_REQUEST);
    }



    @Override
    public UsuarioDTO register(UsuarioDTO.UsuarioCrearDto userDto) {
        Optional<Usuario> optionalUser = repo.findOneByUser(userDto.user());
        if (optionalUser.isPresent()) {
            throw new ModelNotFoundException("Login already exists", HttpStatus.BAD_REQUEST);
        }
        Usuario user = userMapper.toEntityFromCADTO(userDto);
        user.setClave(passwordEncoder.encode(CharBuffer.wrap(userDto.clave()))); //descomenta al trabajar spring security
        user.setClave(userDto.clave().toString());
        Usuario savedUser = repo.save(user);
        Rol r;
        switch (userDto.rol()){
            case "ADMIN":{
                r=rolService.getByNombre(Rol.RolNombre.ADMIN).orElse(null);
            } break;
            case "DBA":{
                r=rolService.getByNombre(Rol.RolNombre.DBA).orElse(null);
            } break;
            default:{
                r=rolService.getByNombre(Rol.RolNombre.USER).orElse(null);
            } break;
        }

        /*UsuarioRol u=new UsuarioRol();
        u.setRol(r);
        u.setUsuario(savedUser);
        iurService.save(u);
        */

        iurService.save(UsuarioRol.builder()
                .usuario(savedUser)
                .rol(r)
                .build());
        return userMapper.toDTO(savedUser);
    }

    @Override
    public List<Usuario> findAll() {
        return List.of();
    }
}

