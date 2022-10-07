package com.example.tictactoe.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Game {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	private char currentPlayerSign;

	@ManyToMany
    @JoinTable(
            name = "players_games",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "player_id")
        )
	private List<Player> players = new ArrayList<Player>();

	private Long currentPlayer;
	
	@OneToOne(cascade = {CascadeType.ALL})
	private GameState gamestate;
	
	@OneToOne(cascade = {CascadeType.ALL})
	private Board board;
	
	public Game() {
		this.currentPlayerSign='O';
		this.gamestate = new GameState(this.id);
		this.board = new Board();
	}

	public Board getBoard() {
		return this.board;
	}
	
	public char getCurrentPlayerSign() {
		return this.currentPlayerSign;
	}
	public void setCurrentPlayerSign(char playerSign) {
		this.currentPlayerSign = playerSign;
	}

	public GameState getGameState() {
		return this.gamestate;
	}
	public Long getGameStateId() {
		return this.gamestate.getGameStateId();
	}

	public Long getPlayer1() {
		return this.players.get(0).getPlayerId();
	}

	public Long getPlayer2() {
		return this.players.get(1).getPlayerId();
	}
	public List<Player> getPlayers(){
		return this.players;
	}
	public void setPlayers(Player player1,Player player2){
		this.players.add(player1);
		this.players.add(player2);
	}
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Long getCurrentPlayer() {
		return this.currentPlayer;
	}
	public void setCurrentPlayer(Long currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
}
