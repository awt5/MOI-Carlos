package org.jalasoft.moi.controller.services;


import org.jalasoft.moi.controller.repository.UserRepository;
import org.jalasoft.moi.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
public class UserServiceIntegrationTest {

    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {
        @Bean
        public UserService userService() {
            return new UserService();
        }
    }
    @Autowired
    private UserService service;
    @MockBean
    private UserRepository repository;

    @Before
    public void setUp() {
        User usertest = new User();
        usertest.setFirstName("juan");
        usertest.setLastName("perez");
        usertest.setUserName("juancito");
        usertest.setPassword("123");
        usertest.setEmail("juan@email.com");
        usertest.setRol("user");
        repository.save(usertest);

        Mockito.when(repository.findByUserName(usertest.getFirstName()))
                .thenReturn(usertest);
    }
    @Test
    public void whenValidName_thenUserShouldBeFound() {
        String name = "juan";
        User found = service.getUserByUserName(name);
        assertTrue(found.getFirstName().contains(name));
    }
    @Test
    public void whenUserFound_thenVerifyHisLastName() {
        String name = "juan";
        String lastname = "perez";
        User found = service.getUserByUserName(name);
        assertTrue(found.getLastName().contains(lastname));
    }
    @Test
    public void whenUserFound_thenVerifyHisUserName() {
        String name = "juan";
        String userName = "juancito";
        User found = service.getUserByUserName(name);
        assertTrue(found.getUserName().contains(userName));
    }
    @Test
    public void whenUserFound_thenVerifyHisPassword() {
        String name = "juan";
        String pass = "123";
        User found = service.getUserByUserName(name);
        assertTrue(found.getPassword().contains(pass));
    }
    @Test
    public void whenUserFound_thenVerifyHisEmail() {
        String name = "juan";
        String email = "juan@email.com";
        User found = service.getUserByUserName(name);
        assertTrue(found.getEmail().contains(email));
    }
    @Test
    public void whenUserFound_thenVerifyHisRol() {
        String name = "juan";
        String rol = "user";
        User found = service.getUserByUserName(name);
        assertTrue(found.getRol().contains(rol));
    }
}
