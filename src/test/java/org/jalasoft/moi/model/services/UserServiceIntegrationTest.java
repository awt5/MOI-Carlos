package org.jalasoft.moi.model.services;


import org.jalasoft.moi.controller.repository.UserRepository;
import org.jalasoft.moi.controller.services.UserService;
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
        public UserService employeeService() {
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
        repository.save(usertest);

        Mockito.when(repository.findByUserName(usertest.getFirstName()))
                .thenReturn(usertest);
    }
    @Test
    public void whenValidName_thenEmployeeShouldBeFound() {
        String name = "juan";
        User found = service.getUserByUserName(name);

        assertTrue(found.getFirstName().contains(name));
    }
}
