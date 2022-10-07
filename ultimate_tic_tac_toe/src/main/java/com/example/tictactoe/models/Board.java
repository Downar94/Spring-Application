package com.example.tictactoe.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Board {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	private char[] w1;
	private char[] w2;
	private char[] w3;
	private char[] w4;
	private char[] w5;
	private char[] w6;
	private char[] w7;
	private char[] w8;
	private char[] w9;
	private char[] w10;

	public static final char[] EMPTY_ROW = { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
	
	public Board() {
		this.w1 = EMPTY_ROW;
		this.w2 = EMPTY_ROW;
		this.w3 = EMPTY_ROW;
		this.w4 = EMPTY_ROW;
		this.w5 = EMPTY_ROW;
		this.w6 = EMPTY_ROW;
		this.w7 = EMPTY_ROW;
		this.w8 = EMPTY_ROW;
		this.w9 = EMPTY_ROW;
		this.w10 = EMPTY_ROW;
	}
	
	public Long getBoardId() {
		return id;
	}
	public char[][] getBoard() {
		char[][] matrix = { { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' } };
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++)
				if (i == 0) {
					matrix[i][j] = this.w1[j];
				} else if (i == 1) {
					matrix[i][j] = this.w2[j];
				} else if (i == 2) {
					matrix[i][j] = this.w3[j];
				} else if (i == 3) {
					matrix[i][j] = this.w4[j];
				} else if (i == 4) {
					matrix[i][j] = this.w5[j];
				} else if (i == 5) {
					matrix[i][j] = this.w6[j];
				} else if (i == 6) {
					matrix[i][j] = this.w7[j];
				} else if (i == 7) {
					matrix[i][j] = this.w8[j];
				} else if (i == 8) {
					matrix[i][j] = this.w9[j];
				} else {
					matrix[i][j] = this.w10[j];
				}
		}
		return matrix;
	}
	
	public void setBoard(int column, int row, char currentPlayerSign) {
		if (1 <= column && column < 11) {
			if (row == 1 && this.w1[column - 1] == ' ') {
				this.w1[column - 1] = currentPlayerSign;
			} else if (row == 2 && this.w2[column - 1] == ' ') {
				this.w2[column - 1] = currentPlayerSign;
			} else if (row == 3 && this.w3[column - 1] == ' ') {
				this.w3[column - 1] = currentPlayerSign;
			} else if (row == 4 && this.w4[column - 1] == ' ') {
				this.w4[column - 1] = currentPlayerSign;
			} else if (row == 5 && this.w5[column - 1] == ' ') {
				this.w5[column - 1] = currentPlayerSign;
			} else if (row == 6 && this.w6[column - 1] == ' ') {
				this.w6[column - 1] = currentPlayerSign;
			} else if (row == 7 && this.w7[column - 1] == ' ') {
				this.w7[column - 1] = currentPlayerSign;
			} else if (row == 8 && this.w8[column - 1] == ' ') {
				this.w8[column - 1] = currentPlayerSign;
			} else if (row == 9 && this.w9[column - 1] == ' ') {
				this.w9[column - 1] = currentPlayerSign;
			} else if (row == 10 && this.w10[column - 1] == ' ') {
				this.w10[column - 1] = currentPlayerSign;
			} else {
				return;
			}
	}	
}
	public char[] getRow(int w) {
		if(w==1) {
		return this.w1;
		}
		else if(w==2) {
		return this.w2;
		}
		else if(w==3) {
		return this.w3;
		}
		else if(w==4) {
		return this.w4;
		}
		else if(w==5) {
		return this.w5;
		}
		else if(w==6) {
		return this.w6;
		}
		else if(w==7) {
		return this.w7;
		}
		else if(w==8) {
		return this.w8;
		}
		else if(w==9) {
		return this.w9;
		}
		else {
		return this.w10;
		}
	}
	
	
}
