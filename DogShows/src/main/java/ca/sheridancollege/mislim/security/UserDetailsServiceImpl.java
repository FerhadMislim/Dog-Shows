package ca.sheridancollege.mislim.security;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.sheridancollege.mislim.repositories.DogShowRepository;
import ca.sheridancollege.mislim.repositories.SecurityRepository;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	@Lazy
	SecurityRepository secRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//find my user
		ca.sheridancollege.mislim.beans.User user = secRepo.findUserAccount(username);
		System.out.println(user);	//no user was found 
		if (user == null) {
			throw new UsernameNotFoundException("User" + username + "was not found.");
		}
		
		List<String> roleNames = secRepo.getRolesById(user.getUserId());
		
		//change the list of role names into a list of GrantedAuthority
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		if (roleNames != null) {
			for (String role : roleNames) {
				grantList.add(new SimpleGrantedAuthority(role));
			}
		}
		
		User springUser = new User(user.getUserName(),user.getEncryptedPassword(), grantList);
		return (UserDetails)springUser;
	}

}
