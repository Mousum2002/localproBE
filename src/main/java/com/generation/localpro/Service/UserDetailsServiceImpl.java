package com.generation.localpro.Service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.generation.localpro.model.PortalUser;
import com.generation.localpro.repository.PortalUserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
private PortalUserRepository uRepo;
	
	public UserDetailsServiceImpl(PortalUserRepository uRepo) {
		this.uRepo = uRepo;
	}

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    PortalUser user = uRepo.findByUserName(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    
    // blocca l'accesso se bannato
    if (user.isBanned()) {
        throw new UsernameNotFoundException("Account bannato. Contatta l'amministratore.");
    }
    
    return org.springframework.security.core.userdetails.User.builder()
        .username(username)
        .password(user.getPassword())
        .roles(user.getRoles().toArray(new String[0]))
        .build();
}

	
  
}
