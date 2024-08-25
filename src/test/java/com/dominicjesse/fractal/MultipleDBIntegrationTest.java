package com.dominicjesse.fractal;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.dominicjesse.blog.BlogApplication;
import com.dominicjesse.blog.mysql.entity.Role;
import com.dominicjesse.blog.mysql.entity.User;
import com.dominicjesse.blog.mysql.repository.RoleRepository;
import com.dominicjesse.blog.mysql.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement
@ContextConfiguration(classes = BlogApplication.class)
public class MultipleDBIntegrationTest {
 
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    @Transactional("userTransactionManager")
    public void whenCreatingUser_thenCreated() {
        User user = new User();
        user.setEmail("john@test.com");
        user.setPassword("encryptthis123$");
        user = userRepository.save(user);

        assertNotNull(userRepository.findById(user.getId()));
    }

    @Test
    @Transactional("userTransactionManager")
    public void whenCreatingUsersWithSameEmail_thenRollback() {
        User user1 = new User();
        user1.setEmail("john@test.com");
        user1.setPassword("encryptthis123$");
        user1 = userRepository.save(user1);
        assertNotNull(userRepository.findById(user1.getId()));

        User user2 = new User();
        user2.setEmail("john@test.com");
        user2.setPassword("encryptthat123$");
        try {
            user2 = userRepository.save(user2);
        } catch (DataIntegrityViolationException e) {
        }

        assertNull(userRepository.findById(user2.getId()));
    }

    @Test
    @Transactional("productTransactionManager")
    public void whenCreatingProduct_thenCreated() {
        Role role = new Role();
        role.setName("Junit_TestUser_a");
        role = roleRepository.save(role);

        assertNotNull(roleRepository.findById(role.getId()));
    }
}
