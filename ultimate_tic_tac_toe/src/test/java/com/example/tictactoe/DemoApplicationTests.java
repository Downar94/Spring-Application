package com.example.tictactoe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import dto.MoveTo;
import dto.PlayerTo;


//Integration tests
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class DemoApplicationTests {
	private String playerId;
	private String lost;
	private String wins;
	private String draws;
	private String gameId;
	private String currentPlayer;
	private String gameState;
	private String firstPlayer;
	private String secondPlayer;
	private String drawState;
	private String gameLoser;
	private String gameWinner;	

    @Autowired
    private MockMvc mvc;
	@BeforeEach
	public void setUp() {
		playerId = "\"playerId\":";
		lost = "\"lost\":";
		wins = "\"wins\":";
		draws = "\"draws\":";
		gameId = "\"id\":";
		currentPlayer = "\"currentPlayer\":";
		gameState = "\"state\":";
		firstPlayer = "\"player1\":";
		secondPlayer = "\"player2\":";
		drawState = "\"draw\":";
		gameLoser = "\"loser\":";
		gameWinner = "\"winner\":";
	}
    @Test
    @Order(1) 
    //Checking if new Player is created correctly
    public void newPlayerTest() throws Exception {
    	
    	PlayerTo playerTo = new PlayerTo();
    	playerTo.setId(1L);
    	playerTo.setWins();
    	playerTo.setLost();
    	playerTo.setDraws();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=objectWriter.writeValueAsString(playerTo);
        
    	  RequestBuilder request = MockMvcRequestBuilders.post("/game/newPlayer").contentType(MediaType.APPLICATION_JSON_UTF8)
    			  .content(requestJson);
    	  MvcResult result = mvc.perform(request).andExpect(status().isOk()).andReturn();
    	    	  
    	  assertTrue(result.getResponse().getContentAsString().contains(playerId+1) 
    			  && result.getResponse().getContentAsString().contains(lost+0)
    			  && result.getResponse().getContentAsString().contains(wins+0)
    			  && result.getResponse().getContentAsString().contains(draws+0)
    			  );
    }
    
    @Test
    @Order(2) 
    //Checking if we can get Player by its id
    public void getPlayerTest() throws Exception {
    	  
        	this.mvc.perform(get("/game/player/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.playerId", org.hamcrest.Matchers.is(1)));
    }
    
    @Test
    @Order(3) 
    //Checking returned content after creating a new game
    public void newGameTest() throws Exception {
    	List<PlayerTo> playerToList = new ArrayList<>();
    	
       	PlayerTo playerTo = new PlayerTo();
    	playerTo.setId(2L);
    	playerTo.setWins();
    	playerTo.setLost();
    	playerTo.setDraws();
    	
      	PlayerTo playerTo2 = new PlayerTo();
    	playerTo2.setId(3L);
    	playerTo2.setWins();
    	playerTo2.setLost();
    	playerTo2.setDraws();
    	
        playerToList.add(playerTo);
        playerToList.add(playerTo2);
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String secondPlayer=objectWriter.writeValueAsString(playerTo);
        String thirdPlayer=objectWriter.writeValueAsString(playerTo2);
        String playerList=objectWriter.writeValueAsString(playerToList);
        
    	  RequestBuilder requestSecondPlayer = MockMvcRequestBuilders.post("/game/newPlayer")
    			  .contentType(MediaType.APPLICATION_JSON_UTF8)
    			  .content(secondPlayer);
    	  
    	  RequestBuilder requestThirdPlayer = MockMvcRequestBuilders.post("/game/newPlayer")
    			  .contentType(MediaType.APPLICATION_JSON_UTF8)
    			  .content(thirdPlayer);
    	  
    	  RequestBuilder requestplayerList = MockMvcRequestBuilders.post("/game/newGame")
    			  .contentType(MediaType.APPLICATION_JSON_UTF8)
    			  .content(playerList);
    	  
  	      mvc.perform(requestSecondPlayer).andExpect(status().isOk());
  	      mvc.perform(requestThirdPlayer).andExpect(status().isOk());
  	      
  	      MvcResult result = mvc.perform(requestplayerList).andExpect(status().isOk()).andReturn();
  	        
    	  assertTrue(result.getResponse().getContentAsString().contains(gameId+1)
    			  && result.getResponse().getContentAsString().contains(currentPlayer+2)
    			  && result.getResponse().getContentAsString().contains(gameState+"\"ACTIVE\"")
    			  && result.getResponse().getContentAsString().contains(drawState+false)
    			  && result.getResponse().getContentAsString().contains(gameLoser+null)
    			  && result.getResponse().getContentAsString().contains(gameWinner+null) 
  
    			  ); 			 
    }
    
    @Test
    @Order(4) 
  //Checking returned content after single move performed by the player
    public void singleMoveTest() throws Exception {
    	
     	  MoveTo moveTo = new MoveTo();
     	  moveTo.setGameId(1L);
     	  moveTo.setPlayerId(2L);
     	  moveTo.setRow(10);
     	  moveTo.setColumn(10);
     	  
          ObjectMapper mapper = new ObjectMapper();
          mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
          ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
          String move=objectWriter.writeValueAsString(moveTo);
          
    	  RequestBuilder requestMove = MockMvcRequestBuilders.put("/game/move")
    			  .contentType(MediaType.APPLICATION_JSON_UTF8)
    			  .content(move);
    	
    	  MvcResult result = mvc.perform(requestMove).andExpect(status().isOk()).andReturn();
    	      	  
    	  assertTrue(
    			  result.getResponse().getContentAsString().contains(currentPlayer+3)
    			  && result.getResponse().getContentAsString().contains(gameState+"\"IN_PROGRESS\"")
    			  && result.getResponse().getContentAsString().contains(drawState+"false")
    			  && result.getResponse().getContentAsString().contains(gameLoser+"null")
    			  && result.getResponse().getContentAsString().contains(gameWinner+"null") 
    			  );  			 
    }
    
    @Test
    @Order(5)
    //Checking returned board content after single move
    public void singleMoveBoardTest() throws Exception {

    	MvcResult result = this.mvc.perform(get("/game/board/1")).andExpect(status().isOk()).andReturn();

   	  assertTrue(
			  result.getResponse().getContentAsString().contains("O")
			  );
   	  assertFalse(
			  result.getResponse().getContentAsString().contains("X")
			  );
		 
  }

    @Test
    @Order(6) 
    // Checking returned content after performing a winning sequence of moves in columns
    public void verticalWinMoveSequenceTest() throws Exception {
  	  int row = 1;
  	  int column = 1;
  	  MvcResult result=null;
  	  
   	  MoveTo moveTo = new MoveTo();
   	  moveTo.setGameId(1L);
   	  moveTo.setPlayerId(2L);
   	  moveTo.setRow(1);
   	  moveTo.setColumn(1);
   	  
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String move=objectWriter.writeValueAsString(moveTo);
        
  	  RequestBuilder requestMove = MockMvcRequestBuilders.put("/game/move")
  			  .contentType(MediaType.APPLICATION_JSON_UTF8)
  			  .content(move); 	
    	  
       	  for(int i=0;i<5;i++) {
       		  if(i==4) {
       		   	  moveTo.setPlayerId(3L);
       		   	  moveTo.setRow(row+i);
       		   	  moveTo.setColumn(column);
       		      move=objectWriter.writeValueAsString(moveTo);
       		   requestMove = MockMvcRequestBuilders.put("/game/move")
       	  			  .contentType(MediaType.APPLICATION_JSON_UTF8)
       	  			  .content(move);
       		      result = mvc.perform(requestMove).andExpect(status().isOk()).andReturn();
       		  }
       		  else {
       		   	  moveTo.setPlayerId(3L);
       		   	  moveTo.setRow(row+i);
       		   	  moveTo.setColumn(column);
       		      move=objectWriter.writeValueAsString(moveTo);
       		   requestMove = MockMvcRequestBuilders.put("/game/move")
       	  			  .contentType(MediaType.APPLICATION_JSON_UTF8)
       	  			  .content(move);
       		       mvc.perform(requestMove).andExpect(status().isOk()).andReturn();
        		   	  moveTo.setPlayerId(2L);
           		   	  moveTo.setRow(row+i);
           		   	  moveTo.setColumn(column+1);
           		      move=objectWriter.writeValueAsString(moveTo);
           		   requestMove = MockMvcRequestBuilders.put("/game/move")
           	  			  .contentType(MediaType.APPLICATION_JSON_UTF8)
           	  			  .content(move);
           		      result = mvc.perform(requestMove).andExpect(status().isOk()).andReturn();
       		  }
       	  }      	  

       	  assertTrue(
    			  result.getResponse().getContentAsString().contains(gameState+"\"FINISHED\"")
    			  && result.getResponse().getContentAsString().contains(drawState+false)
    			  && result.getResponse().getContentAsString().contains(gameLoser+2)
    			  && result.getResponse().getContentAsString().contains(gameWinner+3) 
    			  );
    }
    
    @Test
    @Order(7) 
    // Checking player statistics after win
    public void winPlayerStatistics() throws Exception {
  	  
    	  RequestBuilder request = MockMvcRequestBuilders.get("/game/player/3");
    	  MvcResult result = mvc.perform(request).andExpect(status().isOk()).andReturn(); 
    	  
       	  assertTrue(
       			result.getResponse().getContentAsString().contains(playerId+3) 
  			  && result.getResponse().getContentAsString().contains(lost+0)
  			  && result.getResponse().getContentAsString().contains(wins+1)
  			  && result.getResponse().getContentAsString().contains(draws+0)
    			  );
    }
    
    @Test
    @Order(8) 
    // Checking player statistics after losing
    public void loserPlayerStatistics() throws Exception {
  	  
    	  RequestBuilder request = MockMvcRequestBuilders.get("/game/player/2");
    	  MvcResult result = mvc.perform(request).andExpect(status().isOk()).andReturn(); 
    	  
       	  assertTrue(
       			result.getResponse().getContentAsString().contains(playerId+2) 
  			  && result.getResponse().getContentAsString().contains(lost+1)
  			  && result.getResponse().getContentAsString().contains(wins+0)
  			  && result.getResponse().getContentAsString().contains(draws+0)
    			  );
    }
    
    @Test
    @Order(9) 
    // Checking returned content after performing a winning sequence of moves in rows
    public void horizontalWinMoveSequenceTest() throws Exception {
    	  int row = 1;
    	  int column = 1;
    	  MvcResult result=null;
    	  
    	List<PlayerTo> playerToList = new ArrayList<>();
    	
       	PlayerTo playerTo = new PlayerTo();
    	playerTo.setId(5L);
    	playerTo.setWins();
    	playerTo.setLost();
    	playerTo.setDraws();
    	
      	PlayerTo playerTo2 = new PlayerTo();
    	playerTo2.setId(6L);
    	playerTo2.setWins();
    	playerTo2.setLost();
    	playerTo2.setDraws();
    	
        playerToList.add(playerTo);
        playerToList.add(playerTo2);
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String secondPlayer=objectWriter.writeValueAsString(playerTo);
        String thirdPlayer=objectWriter.writeValueAsString(playerTo2);
        String playerList=objectWriter.writeValueAsString(playerToList);
        
    	  RequestBuilder requestSecondPlayer = MockMvcRequestBuilders.post("/game/newPlayer")
    			  .contentType(MediaType.APPLICATION_JSON_UTF8)
    			  .content(secondPlayer);
    	  
    	  RequestBuilder requestThirdPlayer = MockMvcRequestBuilders.post("/game/newPlayer")
    			  .contentType(MediaType.APPLICATION_JSON_UTF8)
    			  .content(thirdPlayer);
    	  
    	  RequestBuilder requestplayerList = MockMvcRequestBuilders.post("/game/newGame")
    			  .contentType(MediaType.APPLICATION_JSON_UTF8)
    			  .content(playerList);
    	  
  	      mvc.perform(requestSecondPlayer).andExpect(status().isOk());
  	      mvc.perform(requestThirdPlayer).andExpect(status().isOk());
  	      
  	      mvc.perform(requestplayerList).andExpect(status().isOk());
    	  
    	  MoveTo moveTo = new MoveTo();
     	  moveTo.setGameId(2L);
     	  moveTo.setPlayerId(5L);
     	  moveTo.setRow(1);
     	  moveTo.setColumn(6);
     	  
          String move=objectWriter.writeValueAsString(moveTo);
          
      	  RequestBuilder requestMove = MockMvcRequestBuilders.put("/game/move")
      			  .contentType(MediaType.APPLICATION_JSON_UTF8)
      			  .content(move);
      	
      	   mvc.perform(requestMove).andExpect(status().isOk());
      	   
        	  for(int i=0;i<5;i++) {
           		  if(i==4) {
           		   	  moveTo.setPlayerId(5L);
           		   	  moveTo.setRow(row);
           		   	  moveTo.setColumn(column+i);
           		      move=objectWriter.writeValueAsString(moveTo);
           		      requestMove = MockMvcRequestBuilders.put("/game/move")
           	  		  .contentType(MediaType.APPLICATION_JSON_UTF8)
           	  		  .content(move);
           		      result = mvc.perform(requestMove).andExpect(status().isOk()).andReturn();
           		  }
           		  else {
           		   	  moveTo.setPlayerId(5L);
           		   	  moveTo.setRow(row);
           		   	  moveTo.setColumn(column+i);
           		      move=objectWriter.writeValueAsString(moveTo);
           		      requestMove = MockMvcRequestBuilders.put("/game/move")
           	  		  .contentType(MediaType.APPLICATION_JSON_UTF8)
           	  		  .content(move);
           		       mvc.perform(requestMove).andExpect(status().isOk()).andReturn();
            		   moveTo.setPlayerId(6L);
               		   moveTo.setRow(row+1);
               		   moveTo.setColumn(column+i);
               		   move=objectWriter.writeValueAsString(moveTo);
               		   requestMove = MockMvcRequestBuilders.put("/game/move")
               	  	   .contentType(MediaType.APPLICATION_JSON_UTF8)
               	  	   .content(move);
               		   result = mvc.perform(requestMove).andExpect(status().isOk()).andReturn();
           		  }
           	  }    
    	  
       	  assertTrue(
    			  result.getResponse().getContentAsString().contains(gameState+"\"FINISHED\"")
    			  && result.getResponse().getContentAsString().contains(drawState+false)
    			  && result.getResponse().getContentAsString().contains(gameLoser+6)
    			  && result.getResponse().getContentAsString().contains(gameWinner+5) 
    			  );
    }
    
    @Test
    @Order(10) 
    // Checking returned content after performing a diagonal winning sequence of moves 
    public void diagonalWinMoveSequenceTest() throws Exception {
    	  int row = 1;
    	  int column = 1;
    	  MvcResult result=null;
    	List<PlayerTo> playerToList = new ArrayList<>();
    	
       	PlayerTo playerTo = new PlayerTo();
    	playerTo.setId(7L);
    	playerTo.setWins();
    	playerTo.setLost();
    	playerTo.setDraws();
    	
      	PlayerTo playerTo2 = new PlayerTo();
    	playerTo2.setId(8L);
    	playerTo2.setWins();
    	playerTo2.setLost();
    	playerTo2.setDraws();
    	
        playerToList.add(playerTo);
        playerToList.add(playerTo2);
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();
        String secondPlayer=objectWriter.writeValueAsString(playerTo);
        String thirdPlayer=objectWriter.writeValueAsString(playerTo2);
        String playerList=objectWriter.writeValueAsString(playerToList);
        
    	  RequestBuilder requestSecondPlayer = MockMvcRequestBuilders.post("/game/newPlayer")
    			  .contentType(MediaType.APPLICATION_JSON_UTF8)
    			  .content(secondPlayer);
    	  
    	  RequestBuilder requestThirdPlayer = MockMvcRequestBuilders.post("/game/newPlayer")
    			  .contentType(MediaType.APPLICATION_JSON_UTF8)
    			  .content(thirdPlayer);
    	  
    	  RequestBuilder requestplayerList = MockMvcRequestBuilders.post("/game/newGame")
    			  .contentType(MediaType.APPLICATION_JSON_UTF8)
    			  .content(playerList);
    	  
  	      mvc.perform(requestSecondPlayer).andExpect(status().isOk());
  	      mvc.perform(requestThirdPlayer).andExpect(status().isOk());
  	      
  	      mvc.perform(requestplayerList).andExpect(status().isOk());
    	  
    	  MoveTo moveTo = new MoveTo();
     	  moveTo.setGameId(3L);
     	  moveTo.setPlayerId(7L);
     	  moveTo.setRow(6);
     	  moveTo.setColumn(6);
     	  
          String move=objectWriter.writeValueAsString(moveTo);
          
      	  RequestBuilder requestMove = MockMvcRequestBuilders.put("/game/move")
      			  .contentType(MediaType.APPLICATION_JSON_UTF8)
      			  .content(move);
      	
      	   mvc.perform(requestMove).andExpect(status().isOk());
      	   
        	  for(int i=0;i<5;i++) {
           		  if(i==4) {
           		   	  moveTo.setPlayerId(7L);
           		   	  moveTo.setRow(row+i);
           		   	  moveTo.setColumn(column+i);
           		      move=objectWriter.writeValueAsString(moveTo);
           		      requestMove = MockMvcRequestBuilders.put("/game/move")
           	  			  .contentType(MediaType.APPLICATION_JSON_UTF8)
           	  			  .content(move);
           		      result = mvc.perform(requestMove).andExpect(status().isOk()).andReturn();
           		  }
           		  else {
           		   	  moveTo.setPlayerId(7L);
           		   	  moveTo.setRow(row+i);
           		   	  moveTo.setColumn(column+i);
           		      move=objectWriter.writeValueAsString(moveTo);
           		      requestMove = MockMvcRequestBuilders.put("/game/move")
           	  			  .contentType(MediaType.APPLICATION_JSON_UTF8)
           	  			  .content(move);
           		       mvc.perform(requestMove).andExpect(status().isOk()).andReturn();
            		   	  moveTo.setPlayerId(8L);
               		   	  moveTo.setRow(row+1);
               		   	  moveTo.setColumn(column+7);
               		      move=objectWriter.writeValueAsString(moveTo);
               		      requestMove = MockMvcRequestBuilders.put("/game/move")
               	  			  .contentType(MediaType.APPLICATION_JSON_UTF8)
               	  			  .content(move);
               		      result = mvc.perform(requestMove).andExpect(status().isOk()).andReturn();
           		  }
           	  }    
    	  
           	  assertTrue(
        			  result.getResponse().getContentAsString().contains(gameState+"\"FINISHED\"")
        			  && result.getResponse().getContentAsString().contains(drawState+false)
        			  && result.getResponse().getContentAsString().contains(gameLoser+8)
        			  && result.getResponse().getContentAsString().contains(gameWinner+7) 
        			  );

    }
}
