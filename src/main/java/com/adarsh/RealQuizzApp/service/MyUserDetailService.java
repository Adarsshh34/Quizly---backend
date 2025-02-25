package com.adarsh.RealQuizzApp.service;

import com.adarsh.RealQuizzApp.modal.UserPrincipal;
import com.adarsh.RealQuizzApp.modal.Users;
import com.adarsh.RealQuizzApp.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user1 = repo.findByUsername(username);

        if(user1 ==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("user not found");
        }

        return new UserPrincipal(user1);
    }
}
