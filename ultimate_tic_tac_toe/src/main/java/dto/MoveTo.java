package dto;

import lombok.Data;

@Data
public class MoveTo {
	
	private Long gameId;
	private int column;
	private int row;
	private Long playerId;
	
	public Long getGameId(){
		return this.gameId;
	}
	public Long getPlayerId(){
		return this.playerId;
	}
	public int getRow(){
		return this.row;
	}
	public int getColumn(){
		return this.column;
	}
	
	public void setGameId(Long gameId){
		this.gameId=gameId;
	}
	public void setPlayerId(Long playerId){
		this.playerId=playerId;
	}
	public void setRow(int row){
		this.row=row;
	}
	public void setColumn(int column){
		this.column=column;
	}
}