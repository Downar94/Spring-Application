package com.example.tictactoe;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class InitialResourcesTests {

	   @Autowired
	    private MockMvc mvc;
	    
	    @Test
	    @Order(1) 
	    //Checking returned content games table before creating new game 
	    public void bGameTest() throws Exception {	  
	    	  RequestBuilder request = MockMvcRequestBuilders.get("/game/all");   	  
	    	  MvcResult result = mvc.perform(request).andReturn();  	  
	    	  assertEquals("[]", result.getResponse().getContentAsString());
	    }
	    @Test
	    @Order(2) 
	    //Checking returned players table content before creating new player
	    public void bPlayerTest() throws Exception {	  
	    	  RequestBuilder request = MockMvcRequestBuilders.get("/game/players");   	  
	    	  MvcResult result = mvc.perform(request).andReturn();  	  
	    	  assertEquals("[]", result.getResponse().getContentAsString());
	    }
	    
	    @Test
	    @Order(3) 
	    //Checking returned game states table content before creating new game
	    public void bgameStateTest() throws Exception {	  
	    	  RequestBuilder request = MockMvcRequestBuilders.get("/game/gameStates");   	  
	    	  MvcResult result = mvc.perform(request).andReturn();  	  
	    	  assertEquals("[]", result.getResponse().getContentAsString());
	    }
	    
	    @Test
	    @Order(4) 
	    //Checking returned boards table content before creating new game
	    public void bBoardTest() throws Exception {	  
	    	  RequestBuilder request = MockMvcRequestBuilders.get("/game/boards");   	  
	    	  MvcResult result = mvc.perform(request).andReturn();  	  
	    	  assertEquals("[]", result.getResponse().getContentAsString());
	    }
	
}
