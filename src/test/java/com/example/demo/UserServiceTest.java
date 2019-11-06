package com.example.demo;

import com.example.demo.exception.ObjectNotFoundException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserService userService;

    @InjectMocks
    UserServiceImpl userServiceImpl;



    List<User> users = new ArrayList<>();


    @Before
    public void before() {
//        System.out.println("Before");
        User user = new User();
        user.setUsername("Ginger");
        user.setPassword("password");
        user.setEmail("mu@g.com");
        user.setId(1L);
        users.add(user);

        User user1 = new User();
        user1.setUsername("Ginger2");
        user1.setPassword("password");
        user1.setEmail("mui@g.com");
        user1.setId(2L);
        users.add(user1);

    }

    @Test
    public void getAllUsers(){

        when(userService.getAllUsers()).thenReturn(users);
        assertEquals(2, userService.getAllUsers().size());
        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void getEmployeeByIdTest_validId()
    {
        User user = new User();
//        user.setUsername("Ginger");
//        user.setPassword("password");
//        user.setEmail("mu@g.com");
//        user.setId(1L);
        when(userService.getById(1L)).thenReturn(users.get(0));

        User emp = userService.getById(1L);

        assertEquals("Ginger", emp.getUsername());
        assertEquals("mu@g.com", emp.getEmail());
    }

    @Test/*(expected = ObjectNotFoundException.class)*/
    public void getEmployeeByIdTest_inValidId()
    {
        when(userService.getById(1L)).thenReturn(users.get(0));

        User emp = userService.getById(11L);
//        System.out.println(emp.toString());

        assertThat( emp).isNull();

    }


}
