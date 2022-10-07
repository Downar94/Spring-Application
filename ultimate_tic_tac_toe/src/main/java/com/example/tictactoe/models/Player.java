package com.example.tictactoe.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name = "players")
public class Player {
	
	@Id
	private Long playerId;
	
    @ManyToMany(mappedBy = "players")
	private List<Game> games= new ArrayList<Game>();
	
	private int Wins;
	private int Lost;
	private int Draws;
	public Player() {
	this.playerId=3L;
    this.Wins = 0;
    this.Lost = 0;
    this.Draws = 0;
	}
	public Player(Long id) {
	this.playerId = id;
    this.Wins = 0;
    this.Lost = 0;
    this.Draws = 0;
	}
	public Long getPlayerId(){
		return this.playerId;
	}
	public void increaseDraws(){
		this.Draws+=1;
	}
	public int getDraws(){
		return this.Draws;
	}
	public void increaseLost(){
		this.Lost+=1;
	}
	public int getLost(){
		return this.Lost;
	}
	public void increaseWins(){
		this.Wins+=1;
	}
	public int getWins(){
		return this.Wins;
	}

}
 

