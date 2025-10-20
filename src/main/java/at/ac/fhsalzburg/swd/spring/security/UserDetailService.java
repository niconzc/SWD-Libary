package at.ac.fhsalzburg.swd.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import at.ac.fhsalzburg.swd.spring.model.User;
import at.ac.fhsalzburg.swd.spring.services.UserService;

@Service
public class UserDetailService implements UserDetailsService {

	@Autowired
	private UserService UserService;
	
	@Override
    public UserDetails loadUserByUsername(String username) {
    	
		User user = UserService.getByUsername(username);
		if (user==null) throw new UsernameNotFoundException(username);
		return new DemoPrincipal(user.getUsername(), user.getPassword(), user.getRole(), user.getJwttoken());
			
		}
}
