package com.wander.notes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wander.notes.dao.User;
import com.wander.notes.dao.UserRepository;
import com.wander.notes.security.JwtUserFactory;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
        	System.out.println("ANMOL No user found with username : " + username);
        	/*//TEST
        	User u = new User("shashwatg", "$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi", "Shashwat", "Goyal", "reckless1994@gmail.com", true, new Date(-1), Arrays.asList("ROLE_USER"));
        	userRepository.save(u);*/
        	
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }
    	
    	
    	/*if(!username.equalsIgnoreCase("anmol")) {
    		throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
    	}else {
    		List<GrantedAuthority> authorityList = new ArrayList<>();
    		authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
    		authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
    		
    		JwtUser user = new JwtUser(
                    1l,
                    "anmol",
                    "Anmol",
                    "Singh",
                    "reckless1994@gmail.com",
                    "$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi",
                    authorityList,
                    true,
                    new Date(-1)
            );
    		
    		return user;
    	}*/
    }
}
