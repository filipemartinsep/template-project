package com.exemplo.api_user.users.services;

import com.exemplo.api_user.users.models.User;
import com.exemplo.api_user.users.repositories.UserRepository;
import com.exemplo.api_user.users.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found!"));
        return new CustomUserDetails(user);
    }
}
