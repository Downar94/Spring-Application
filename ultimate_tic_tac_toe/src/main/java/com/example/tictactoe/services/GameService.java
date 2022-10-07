package com.example.tictactoe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tictactoe.models.Board;
import com.example.tictactoe.models.Game;
import com.example.tictactoe.models.GameState;
import com.example.tictactoe.models.Player;
import com.example.tictactoe.dao.BoardRepo;
import com.example.tictactoe.dao.GameRepo;
import com.example.tictactoe.dao.GameStateRepo;
import com.example.tictactoe.dao.PlayerRepo;

import dto.MoveTo;
import dto.PlayerTo;

@Service
public class GameService {
	
 private GameRepo gameRepo;
 private PlayerRepo playerRepo;
 private GameStateRepo gameStateRepo;
 private BoardRepo boardRepo;

 @Autowired
 public GameService(GameRepo gameRepo, PlayerRepo playerRepo,GameStateRepo gameStateRepo, BoardRepo boardRepo) {
  this.gameRepo = gameRepo;
  this.playerRepo = playerRepo;
  this.gameStateRepo = gameStateRepo;
  this.boardRepo = boardRepo;
 }

 public Game findById(Long id) {
  return gameRepo.findById(id).orElse(null);
 }
 
 public Player findPlayerById(Long id) {
  return playerRepo.findById(id).orElse(null);
}
 
 public GameState findGameStateById(Long id) {
	  return gameStateRepo.findById(id).orElse(null);
	}
 
 public Board findBoardById(Long id) {
	  return boardRepo.findById(id).orElse(null);
	}

 public Player savePlayer(Player player) { 
	  return playerRepo.save(player);
	}
 public Player savetoPlayer(PlayerTo playerTo) { 
	 Player player = new Player(playerTo.getPlayerToId());
	  return playerRepo.save(player);
	}
 
 public GameState saveGameState(GameState gameState) {
	 return gameStateRepo.save(gameState);
	}
 
 public Game save(Game game) {
  return gameRepo.save(game);
 }
 public Board saveBoard(Board board) {
	 return boardRepo.save(board);
	}

 //additional functionality
 public Iterable<Game> findAll() {
	  return gameRepo.findAll();
	 }
 //additional functionality
 public Iterable<Player> findAllPlayers() {
	  return playerRepo.findAll();
	 }
 //additional functionality
 public Iterable<GameState> findAllGameStates() {
	  return gameStateRepo.findAll();
	 }
 //additional functionality
 public Iterable<Board> findAllBoards() {
	  return boardRepo.findAll();
	 }
//additional functionality
 public void deleteById(Long id) {
  gameRepo.deleteById(id);
 }
 
 //new game creating
 public Game newGame(List<PlayerTo> playerTo) {
	 
	  Player player = findPlayerById(playerTo.get(0).getPlayerToId());
	  Player player2 = findPlayerById(playerTo.get(1).getPlayerToId());	  
	  Game game = new Game();
	  beginNewGame(game, player,player2);
	  
	  GameState gameState = game.getGameState();
	  gameState.setActiveState();
	  
	  Board board = game.getBoard();
	  
	  saveGameState(gameState);
	  savePlayer(player);
	  save(game);
	  saveBoard(board);
	  return game;  
	 }
 
 //single player move
 public Game playerMove(MoveTo moveTo) {
	Game game = findById(moveTo.getGameId());	
	if (game.getCurrentPlayerSign() == 'O' && game.getPlayer1() == moveTo.getPlayerId()) {		
		GameState gameState = game.getGameState();
		gameState.setInProgressState();
		
		move(moveTo.getColumn(), moveTo.getRow(),game);
		if(checkWin(game)==true) {
			gameState.setFinishedState();
			
			Player winnerPlayer = findPlayerById(getWinner(game));
			winnerPlayer.increaseWins();
			gameState.setWinner(winnerPlayer.getPlayerId());
			savePlayer(winnerPlayer);
			
			Player loserPlayer = findPlayerById(game.getPlayer2());
			loserPlayer.increaseLost();
			gameState.setLoser(loserPlayer.getPlayerId());
			savePlayer(loserPlayer);
		}
		else if(checkDraw(game)==true) {
			gameState.setFinishedState();
			gameState.setDraw(true);
			
			Player drawPlayer1 = findPlayerById(game.getPlayer1());
			Player drawPlayer2 = findPlayerById(game.getPlayer2());
			drawPlayer1.increaseDraws();
			drawPlayer2.increaseDraws();
			
			savePlayer(drawPlayer1);
			savePlayer(drawPlayer2);
		}
		saveGameState(gameState);
		
	} else if (game.getCurrentPlayerSign() == 'X' && game.getPlayer2() ==  moveTo.getPlayerId()) {
		GameState gameState = game.getGameState();
		gameState.setInProgressState();
		
		move(moveTo.getColumn(), moveTo.getRow(),game);
		if(checkWin(game)==true) {
			gameState.setFinishedState();
			
			Player winnerPlayer = findPlayerById(getWinner(game));
			winnerPlayer.increaseWins();
			gameState.setWinner(winnerPlayer.getPlayerId());
			savePlayer(winnerPlayer);
			
			Player loserPlayer = findPlayerById(game.getPlayer1());
			loserPlayer.increaseLost();
			gameState.setLoser(loserPlayer.getPlayerId());
			savePlayer(loserPlayer);
		}
		else if(checkDraw(game)==true) {
			gameState.setFinishedState();
			gameState.setDraw(true);
			
			Player drawPlayer1 = findPlayerById(game.getPlayer1());
			Player drawPlayer2 = findPlayerById(game.getPlayer2());
			drawPlayer1.increaseDraws();
			drawPlayer2.increaseDraws();
			
			savePlayer(drawPlayer1);
			savePlayer(drawPlayer2);
		}
		saveGameState(gameState);
	}
	  Board board = game.getBoard();
	  
	  saveBoard(board);
	  save(game);
	  return game;  
	 } 

	public void move(int column, int row, Game game) {
	    game.getBoard().setBoard(column, row, game.getCurrentPlayerSign());
		if (checkWin(game) == false) {
			if (game.getCurrentPlayer() == game.getPlayers().get(0).getPlayerId()) {
				game.setCurrentPlayer(game.getPlayers().get(1).getPlayerId());
				game.setCurrentPlayerSign('X');
			} else {
				game.setCurrentPlayer(game.getPlayers().get(0).getPlayerId());
				game.setCurrentPlayerSign('O');
			}
		}
	
}
	public boolean checkDraw(Game game) {
		boolean draw = false;
		for (int i = 0; i < 10; i++) {
			if (game.getBoard().getRow(1)[i] == ' ' || game.getBoard().getRow(2)[i] == ' ' || game.getBoard().getRow(3)[i] == ' ' || game.getBoard().getRow(4)[i] == ' ' || game.getBoard().getRow(5)[i] == ' '
					|| game.getBoard().getRow(6)[i] == ' ' || game.getBoard().getRow(7)[i] == ' ' || game.getBoard().getRow(8)[i] == ' ' || game.getBoard().getRow(9)[i] == ' '
					|| game.getBoard().getRow(10)[i] == ' ') {
				return draw;
			}
		}
		if (checkWin(game) != true) {
			draw = true;
			return draw;
		}
		return draw;
	}
	
	public boolean checkWin(Game game) {
		boolean win = false;
		char sign = game.getCurrentPlayerSign();
		String firstdiagonal = "";
		String seconddiagonal = "";
		// horizontal
		win = checkRow(game.getBoard().getRow(1),game);
		if (win == true) {
			return win;
		}
		win = checkRow(game.getBoard().getRow(2),game);
		if (win == true) {
			return win;
		}
		win = checkRow(game.getBoard().getRow(3),game);
		if (win == true) {
			return win;
		}
		win = checkRow(game.getBoard().getRow(4),game);
		if (win == true) {
			return win;
		}
		win = checkRow(game.getBoard().getRow(5),game);
		if (win == true) {
			return win;
		}
		win = checkRow(game.getBoard().getRow(6),game);
		if (win == true) {
			return win;
		}
		win = checkRow(game.getBoard().getRow(7),game);
		if (win == true) {
			return win;
		}
		win = checkRow(game.getBoard().getRow(8),game);
		if (win == true) {
			return win;
		}
		win = checkRow(game.getBoard().getRow(9),game);
		if (win == true) {
			return win;
		}
		win = checkRow(game.getBoard().getRow(10),game);
		if (win == true) {
			return win;
		}
		// first diagonal
		firstdiagonal += String.valueOf(game.getBoard().getRow(1)[0]);
		firstdiagonal += String.valueOf(game.getBoard().getRow(2)[1]);
		firstdiagonal += String.valueOf(game.getBoard().getRow(3)[2]);
		firstdiagonal += String.valueOf(game.getBoard().getRow(4)[3]);
		firstdiagonal += String.valueOf(game.getBoard().getRow(5)[4]);
		firstdiagonal += String.valueOf(game.getBoard().getRow(6)[5]);
		firstdiagonal += String.valueOf(game.getBoard().getRow(7)[6]);
		firstdiagonal += String.valueOf(game.getBoard().getRow(8)[7]);
		firstdiagonal += String.valueOf(game.getBoard().getRow(9)[8]);
		firstdiagonal += String.valueOf(game.getBoard().getRow(10)[9]);
		if (firstdiagonal.contains(String.valueOf(sign) + String.valueOf(sign) + String.valueOf(sign)
				+ String.valueOf(sign) + String.valueOf(sign)) == true) {
			win = true;
			return win;
		}
		// second diagonal
		seconddiagonal += String.valueOf(game.getBoard().getRow(1)[9]);
		seconddiagonal += String.valueOf(game.getBoard().getRow(2)[8]);
		seconddiagonal += String.valueOf(game.getBoard().getRow(3)[7]);
		seconddiagonal += String.valueOf(game.getBoard().getRow(4)[6]);
		seconddiagonal += String.valueOf(game.getBoard().getRow(5)[5]);
		seconddiagonal += String.valueOf(game.getBoard().getRow(6)[4]);
		seconddiagonal += String.valueOf(game.getBoard().getRow(7)[3]);
		seconddiagonal += String.valueOf(game.getBoard().getRow(8)[2]);
		seconddiagonal += String.valueOf(game.getBoard().getRow(9)[1]);
		seconddiagonal += String.valueOf(game.getBoard().getRow(10)[0]);
		if (seconddiagonal.contains(String.valueOf(sign) + String.valueOf(sign) + String.valueOf(sign)
				+ String.valueOf(sign) + String.valueOf(sign)) == true) {
			win = true;
			return win;
		}

		// columns
		for (int i = 0; i < 10; i++) {
			String column = "";
			column += String.valueOf(game.getBoard().getRow(1)[i]);
			column += String.valueOf(game.getBoard().getRow(2)[i]);
			column += String.valueOf(game.getBoard().getRow(3)[i]);
			column += String.valueOf(game.getBoard().getRow(4)[i]);
			column += String.valueOf(game.getBoard().getRow(5)[i]);
			column += String.valueOf(game.getBoard().getRow(6)[i]);
			column += String.valueOf(game.getBoard().getRow(7)[i]);
			column += String.valueOf(game.getBoard().getRow(8)[i]);
			column += String.valueOf(game.getBoard().getRow(9)[i]);
			column += String.valueOf(game.getBoard().getRow(10)[i]);
			if (column.contains(String.valueOf(sign) + String.valueOf(sign) + String.valueOf(sign)
					+ String.valueOf(sign) + String.valueOf(sign)) == true) {
				win = true;
				return win;
			}
		}
		return win;
	}

	public boolean checkRow(char[] row, Game game) {
		char sign = game.getCurrentPlayerSign();
		int count = 0;
		for (int i = 0; i < 10; i++) {
			if (row[i] == sign) {
				count++;
				if (count == 5) {
					return true;
				}
			} else if (row[i] != sign) {
				count = 0;
			}
		}
		return false;
	}
	
	public Long getWinner(Game game) {
		if (checkWin(game) == true) {
			return game.getCurrentPlayer();
		}
		return 0L;
	}
	
	public void beginNewGame(Game game, Player player1,Player player2){
		game.setPlayers(player1,player2);
		game.setCurrentPlayer(game.getPlayers().get(0).getPlayerId()); 
	}
 
}