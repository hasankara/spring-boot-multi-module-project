package hasan.kara.web.security;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import hasan.kara.domain.entity.Role;
import hasan.kara.domain.entity.User;

public class AuthenticationSystem {
    public static boolean isLogged() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return null != authentication && !("anonymousUser").equals(authentication.getName());
    }
    
    public static boolean isUserAdmin() {
    	final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	boolean isUserAdmin = authentication.getAuthorities().stream()
    	          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN"));
    	return isUserAdmin;
    }
    
    public static boolean isUserModerator() {
    	final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	boolean isUserAdmin = authentication.getAuthorities().stream()
    	          .anyMatch(r -> r.getAuthority().equals("ROLE_MODERATOR"));
    	return isUserAdmin;
    }
    
    public static String getUserName() {
    	final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	 User userPrincipal = (User)authentication.getPrincipal();
    	 return userPrincipal.getUsername();
    }
    
    public static User getUser() {
    	final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	 User userPrincipal = (User)authentication.getPrincipal();
    	 return userPrincipal;
    }
    
    public static void updateUser(User user) {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	User userPrincipal = (User)auth.getPrincipal();
    	userPrincipal.setName(user.getName());
    	userPrincipal.setLastName(user.getLastName());
    	userPrincipal.setRoles(user.getRoles());
    	
    	List<GrantedAuthority> updatedAuthorities = new ArrayList<>();
    	for (Role role : user.getRoles()) {
			updatedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
		}
    	Authentication newAuth = new UsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), updatedAuthorities);
    	SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
    
    public static void logoutUser() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	auth.setAuthenticated(false);
    }

    
}