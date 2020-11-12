package com.example.auth.Services;

import com.example.auth.Entities.AuthUserDetails;
import com.example.auth.Entities.UsersEntity;
import com.example.auth.Repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthUserDetailsService implements UserDetailsService {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<UsersEntity> optionalUsers =  usersRepository.findById(s);
        optionalUsers
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return  optionalUsers
                .map(AuthUserDetails::new).get();
    }
}
