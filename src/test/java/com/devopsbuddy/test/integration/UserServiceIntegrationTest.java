package com.devopsbuddy.test.integration;

import com.devopsbuddy.DevopsbuddyApplication;
import com.devopsbuddy.backend.persistence.domain.backend.Role;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.domain.backend.UserRole;
import com.devopsbuddy.backend.persistence.repositories.UserRepository;
import com.devopsbuddy.backend.service.UserService;
import com.devopsbuddy.enums.PlansEnum;
import com.devopsbuddy.enums.RolesEnum;
import com.devopsbuddy.utils.UsersUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Created by nvishwarupe
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(DevopsbuddyApplication.class)
public class UserServiceIntegrationTest extends AbstractServiceIntegrationTest {

    @Rule public TestName testName = new TestName();


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Test
    public void testCreateNewUser() throws Exception {

        User user = createUser(testName);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());
        // stored password in repository matches the password encrypted that means error is different
        System.out.println("password is "+ user.getPassword());
        Assert.assertTrue(bCryptPasswordEncoder.matches("secret", user.getPassword()));

    }

    @Test // Added by nvishwarupe
    public void testUpdateUserPasswordService() throws Exception {

        User user = createUser(testName);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());

        String newPassword = UUID.randomUUID().toString();

        userService.updateUserPassword(user.getId(), newPassword);

        System.out.println("Before user id" + user.getId());
        System.out.println("Before user email" + user.getEmail());

        user = userService.findByEmail(user.getEmail());

        System.out.println("After user id" + user.getId());
        System.out.println("After user email" + user.getEmail());

        Assert.assertTrue(bCryptPasswordEncoder.matches(newPassword, user.getPassword()));

    }

}