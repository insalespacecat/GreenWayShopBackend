package com.greenwayshop.learning.security;

import com.greenwayshop.learning.api.UserRepository;
import com.greenwayshop.learning.domain.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRepoUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;
    UserRepoUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if(user != null){
            return user;
        }
        throw new UsernameNotFoundException("User " + username + " not found");
    }
}
