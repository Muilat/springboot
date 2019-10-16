package com.example.demo;

import com.example.demo.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
//@WebMvcTest(controllers = UserController.class)
@WebAppConfiguration
public class UserControllerTest {

//    @Autowired
    private MockMvc mockMvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        this.mockMvc = MockMvcBuilders.standaloneSetup().build();
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void getAllUsersAPI() throws Exception
    {

        mockMvc.perform( MockMvcRequestBuilders
                .get("/users/")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())

//                .andExpect(MockMvcResultMatchers.jsonPath("$.users").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty());



    }

    @Test
    public void createUser() throws Exception {
        String uri = "/users/add";
        User user = new User();
        user.setUsername("Ginger");
        user.setPassword("password");
        user.setEmail("mu@g.com");
        ObjectMapper objectMapper = new ObjectMapper();


        String inputJson =objectMapper.writeValueAsString(user);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        System.out.println(content);
//        assertEquals(content, "User is created successfully");
    }


}
