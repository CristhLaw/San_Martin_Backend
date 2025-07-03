package pe.edu.upeu.sysalmacen.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthorizeLogic {

    public boolean hasAccess(String path){
        boolean result = false;

        String methodRole = switch(path){
            case "findAll" -> "ADMIN";
            case "findById", "getBydId" -> "USER,DBA";
            default -> "ROOT";
        };

        String methodRoles[] = methodRole.split(",");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username is: " + auth.getName());

        for(GrantedAuthority grantedAuthority : auth.getAuthorities()){
            String roleUser = grantedAuthority.getAuthority();
            log.info("Role is: " + roleUser);

            for(String role : methodRoles){
                if(roleUser.equalsIgnoreCase(role)){
                    result = true;
                    break;
                }
            }
        }

        return result;
    }
}
