package pe.edu.upeu.sysalmacen.security;



import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.upeu.sysalmacen.model.Usuario;
import pe.edu.upeu.sysalmacen.model.UsuarioRol;
import pe.edu.upeu.sysalmacen.repository.IUsuarioRepository;
import pe.edu.upeu.sysalmacen.repository.IUsuarioRolRepository;

import java.util.ArrayList;
import java.util.List;

//Clase S4
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final IUsuarioRolRepository repo;
    private final IUsuarioRepository repoU;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario u=repoU.findOneByUser(username).orElse(null);
        List<UsuarioRol> user = repo.findOneByUsuarioUser(username);

        if (user == null) {
            throw new UsernameNotFoundException("Username not found: " + username);
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        user.forEach(rol -> {
            roles.add(new SimpleGrantedAuthority(rol.getRol().getNombre().name()));
        });

        return new org.springframework.security.core.userdetails.User(u.getUser(), u.getClave(), roles);
    }
}
