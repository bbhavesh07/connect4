package com.pratilipi.connect4.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

@Service
public class GameService {
	
	private int[][] board;
	private int[] curr;
	private boolean color;
	
	public GameService() {
		reset();
	}
	
	public void reset() {
		this.board = new int[6][7];
		this.color = true;
		this.curr = new int[7];
		Arrays.fill(curr, 5);
	}
	
	public String add(int col) {
		if(curr[col] < 0) return "Invalid";
		board[curr[col]][col] = getNumberByColor();
		if(checkIfWon(curr[col], col))
			return color ? "Yellow wins" : "Red wins";
		color = !color;
		curr[col]--;
		return "Valid";
	}
	
	private int getNumberByColor() {
		return color ? 1 : 2;
	}

	private boolean checkIfWon(int r, int c) {
		return checkX(r, c) || checkY(r, c) || checkDiagonal(r, c);
	}
	
	private boolean checkX(int r, int c) {
		int cnt = 1, j = c-1;
		while(cnt < 4 && j >= 0 && board[r][j--] == getNumberByColor())
			cnt++;
		j = c+1;
		while(cnt < 4 && j < 7 && board[r][j++] == getNumberByColor())
			cnt++;
		return cnt == 4;
	}
	
	private boolean checkY(int r, int c) {
		int cnt = 1, i = r-1;
		while(cnt < 4 && i >= 0 && board[i--][c] == getNumberByColor())
			cnt++;
		i = r+1;
		while(cnt < 4 && i < 6 && board[i++][c] == getNumberByColor())
			cnt++;
		return cnt == 4;
	}
	
	private boolean checkDiagonal(int r, int c) {
		int cnt = 1, i = r+1, j = c-1;
		while(cnt < 4 && i < 6 && j >= 0 && board[i++][j--] == getNumberByColor())
			cnt++;
		i = r-1; j = c+1;
		while(cnt < 4 && i >= 0 && j < 7 && board[i--][j++] == getNumberByColor())
			cnt++;
		i = r-1; j = c-1;
		while(cnt < 4 && i >= 0 && j >= 0 && board[i--][j--] == getNumberByColor())
			cnt++;
		i = r+1;
		j = c+1;
		while(cnt < 4 && i < 6 && j < 7 && board[i++][j++] == getNumberByColor())
			cnt++;
		return cnt == 4;
	}
}
