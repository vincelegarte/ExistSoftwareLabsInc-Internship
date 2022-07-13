package com.activity.four.security.service;

import com.activity.four.security.model.UserPrincipal;
import com.activity.four.security.model.Users;
import com.activity.four.security.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.activity.four.security.service.authorities.ApplicationRole.USER;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {


    private UsersRepository usersRepository;

    @Autowired
    public UserPrincipalDetailsService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = this.usersRepository.findByUsername(username);
        UserPrincipal userPrincipal = new UserPrincipal(user);
        return userPrincipal;
    }
}
