package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebMvcTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private MockMvc mvc;

//	@Test
//	public void getAllUsersAPI() throws Exception
//	{
//
//		mvc.perform( MockMvcRequestBuilders
//				.get("/users")
//				.accept(MediaType.APPLICATION_JSON))
//				.andDo(print())
//				.andExpect(status().isOk())
//				.andExpect(MockMvcResultMatchers.jsonPath("$.users").exists())
//				.andExpect(MockMvcResultMatchers.jsonPath("$.users[*].id").isNotEmpty());
//	}
}
