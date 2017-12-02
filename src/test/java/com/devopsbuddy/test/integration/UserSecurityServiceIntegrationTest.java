package com.devopsbuddy.test.integration;

import com.devopsbuddy.DevopsbuddyApplication;
import com.devopsbuddy.backend.persistence.domain.backend.Role;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy.backend.persistence.repositories.UserRepository;
import com.devopsbuddy.backend.service.UserSecurityService;
import com.devopsbuddy.backend.service.UserService;
import com.devopsbuddy.enums.PlansEnum;
import com.devopsbuddy.enums.RolesEnum;
import com.devopsbuddy.utils.UsersUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by nvishwarupe
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(DevopsbuddyApplication.class)
public class UserSecurityServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testGetUserByUserName()
    {
        Set<UserRole> userRoles = new HashSet<>();
        User basicUser = UsersUtils.createBasicUser();
        basicUser.setUsername("securityDetailsTestUser");
        userRoles.add(new UserRole(basicUser, new Role(RolesEnum.BASIC)));


        User user = userService.createUser(basicUser, PlansEnum.BASIC, userRoles);
        // After adding user retrieve the user
        String username = basicUser.getUsername();
        UserDetails userDetails =  userSecurityService.loadUserByUsername(username);
        Assert.assertNotNull(userDetails);
        Assert.assertNotNull(userDetails.isCredentialsNonExpired());

        // Cleanup delete created user

        userRepository.delete(user.getId());
    }
}

