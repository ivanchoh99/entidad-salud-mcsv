package com.security.authentication.security;

import com.security.authentication.models.entity.Roles;
import com.security.authentication.models.entity.Users;
import com.security.authentication.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private IUserRepo userRepo;

    public Collection<GrantedAuthority> mapAuthorities(Set<Roles> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return new User(
                user.getUsername(),
                user.getPassword(),
                mapAuthorities(user.getRoles())
        );
    }
}
