package com.example.tictactoe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tictactoe.models.Board;
import com.example.tictactoe.models.Game;
import com.example.tictactoe.models.GameState;
import com.example.tictactoe.services.GameService;
import com.example.tictactoe.models.Player;

import dto.MoveTo;
import dto.PlayerTo;
import exceptions.RequestException;

@RestController
@RequestMapping("/game")
public class GameController {

	private GameService games;

	@Autowired
	public GameController(GameService games) {
		this.games = games;
	}
    //methods using DTO classes
	
	//creating new game
	@PostMapping("/newGame")
	public Game newGame(@RequestBody List<PlayerTo> playerTo) {
		return games.newGame(playerTo);
	}
	
	//creating new player
	@PostMapping("/newPlayer")
	public Player newPlayer(@RequestBody PlayerTo playerTo) {
		return games.savetoPlayer(playerTo);
	}
     
	//making move
	@PutMapping("/move")
	public Game playerMove(@RequestBody MoveTo moveTo) {
		try {
			return games.playerMove(moveTo);
		}
		catch(Exception e) {
			 throw new RequestException("Incorrect move");
		}
	}
	
	
	//getting player by id
	@GetMapping("/player/{index}")
	public Player thisPlayer(@PathVariable("index") Long index) {
		Player player = games.findPlayerById(index);	
	    if(player == null) {
	        throw new RequestException("Player  fot found");
	    }
		return player;
	}
	
	
	/*additional*/
	//getting game by id
	@GetMapping("/{index}")
	public Game yourGame(@PathVariable("index") Long index) {
		Game game = games.findById(index);	
	    if(game == null) {
	        throw new RequestException("Game fot found");
	    }
		return game;
	}

	//getting game state by id
	@GetMapping("/gameState/{index}")
	public GameState thisState(@PathVariable("index") Long index) {
		GameState gameState = games.findGameStateById(index);
	    if(gameState == null) {
	        throw new RequestException("GameState fot found");
	    }
		return gameState;
	}
	
	//getting board by id
	@GetMapping("/board/{index}")
	public Board thisBoard(@PathVariable("index") Long index) {
		Board board = games.findBoardById(index);	
	    if(board == null) {
	        throw new RequestException("Board fot found");
	    }
		return board;
	}
	
	
	/*additional*/
	// additional functionality (get all entities)
	@GetMapping("/all")
	public Iterable<Game> getAllGames() {
		if(games.findAll()==null) {
		throw new RequestException("Games fot found");
		}
		return games.findAll();
	}
	@GetMapping("/players")
	public Iterable<Player> getAllPlayers() {
		return games.findAllPlayers();
	}
	@GetMapping("/gameStates")
	public Iterable<GameState> getAllgameStates() {
		return games.findAllGameStates();
	}
	@GetMapping("/boards")
	public Iterable<Board> getAllBoards() {
		return games.findAllBoards();
	}

	/*additional*/
	@DeleteMapping("/{index}")
	public void deleteGame(@RequestParam Long index) {
		games.deleteById(index);		
	}

}