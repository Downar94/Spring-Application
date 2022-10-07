package dto;

import lombok.Data;

@Data
public class PlayerTo {
	private Long playerToId;
	private int Wins;
	private int Lost;
	private int Draws;
	
	public Long getPlayerToId(){
		return this.playerToId;
	}
	public void setId(Long playerToId) {
		this.playerToId=playerToId;
	}
	public void setWins() {
		this.Wins=0;
	}
	public void setLost() {
		this.Lost=0;
	}
	public void setDraws() {
		this.Draws=0;
	}
}
