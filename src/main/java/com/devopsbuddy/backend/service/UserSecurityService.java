package com.devopsbuddy.backend.service;

import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(UserSecurityService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (null == user) {
            LOG.warn("Username {} not found", username);
            throw new UsernameNotFoundException("Username " + username + " not found");
        }

        System.out.println("debug here 2");
        System.out.println(user.getPassword() + "********");
        System.out.println(user.getPassword().length() + " length of password");
        System.out.println("username is " + username);
        if(bCryptPasswordEncoder.matches("secret", user.getPassword() )) {
            System.out.println("user password is " + user.getPassword());
            System.out.println("There is no issue with password match");
        } else
        {
            System.out.println("Passwords do not match");
        }

        return user;
    }
}
