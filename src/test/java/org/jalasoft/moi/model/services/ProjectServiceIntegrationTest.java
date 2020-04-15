package org.jalasoft.moi.model.services;

/*

import org.jalasoft.moi.controller.repository.ProjectRepository;
import org.jalasoft.moi.controller.repository.UserRepository;
import org.jalasoft.moi.controller.services.ProjectService;
import org.jalasoft.moi.controller.services.UserService;
import org.jalasoft.moi.domain.Project;
import org.jalasoft.moi.domain.User;
import org.jalasoft.moi.model.core.Language;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class ProjectServiceIntegrationTest {

    @TestConfiguration
    static class ProjectServiceImplTestContextConfiguration {
        @Bean
        public ProjectService projectService() {
            return new ProjectService();
        }
    }
    @Autowired
    private ProjectService service;
    @MockBean
    private ProjectRepository repository;

    @Before
    public void setUp() {
        Project projectTest = new Project();
        projectTest.setProjectName("test");
        projectTest.setDescription("suma dos numeros");
        projectTest.setLanguage(Language.JAVA);
        projectTest.setPath("/moi/test/java");
        repository.save(projectTest);

        Mockito.when(repository.findByProjectName(projectTest.getProjectName()))
                .thenReturn(projectTest);
    }
    @Test
    public void whenValidName_thenProjectShouldBeFound() {
        String name = "test";
        Project found = service.getProjectByProjectName(name);
        assertTrue(found.getProjectName().contains(name));
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
}
*/
