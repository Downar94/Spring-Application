package com.example.tictactoe.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class GameState {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long gameStateId;
	
	@Enumerated(EnumType.ORDINAL)
	private State state;
	
	private boolean draw;	
	private Long winner;
	private Long loser;
	
	public GameState() {
	}
	
	public GameState(Long gameId) {
		this.gameStateId = gameId;
	}
	
	public void setActiveState() {
		this.state = State.ACTIVE;
	}
	public void setInProgressState() {
		this.state = State.IN_PROGRESS;
	}
	public void setFinishedState() {
		this.state = State.FINISHED;
	}
	public State getState() {
		return this.state;
	}
	public Long getGameStateId() {
		return this.gameStateId;
	}
	public void setLoser(Long loserId) {
		this.loser=loserId;
	}
	public Long getLoser() {
		return this.loser;
	}
	public void setWinner(Long winnerId) {
		this.winner=winnerId;
	}
	public Long getWinner() {
		return this.winner;
	}
	public void setDraw(boolean draw) {
		this.draw = draw;
	}
	public boolean getDraw() {
		return this.draw;
	}


}
