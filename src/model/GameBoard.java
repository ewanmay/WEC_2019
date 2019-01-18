package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


/**
 * 
 * @author Antoine
 *
 */
public class GameBoard {
	/**
	 * 
	 */
	private int sideLength;
	
	/**
	 * 
	 */
	private int board[][];
	
	/**
	 * 
	 */
	public GameBoard(int length) {
		sideLength = length;
		board = new int[length][length]; 
		populateBoard();
	}

	/**
	 * Helper method that populates board.
	 * Used to help keep constructor clean.
	 */
	private void populateBoard() {
		int xPos, yPos;
		Random r;
		boolean loopAgain = true;
		for(int i = 0; i < sideLength; i++ ) {
			
			do {
				r = new Random();
				xPos =  r.nextInt((sideLength - 1 - 0) + 1) + 0;
				r = new Random();
				yPos =  r.nextInt((sideLength - 1 - 0) + 1) + 0;
				
				if(board[xPos][yPos] > -1) {
					board[xPos][yPos] = -1;
					incrementCounts(xPos, yPos);
					loopAgain = false;
				}
			}while(loopAgain);
			loopAgain = true;
		}
		
	}

	/**
	 * Helper method to increment 'adjacent basin counts' around a basin
	 * @param x horizontal position of the basin
	 * @param y vertical position of the basin
	 */
	private void incrementCounts(int x, int y) {
		for(int i = x-1; i <= x+1; i++) {
			for(int j = y - 1; j <= y+1; j++) {
				if((i >= 0) && (i < sideLength) && (j >= 0) && (j < sideLength) && (board[i][j] > -1)) {
					board[i][j]++;
				}
			}
				
		}
		
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int boardAt(int x, int y) {
		if((x >= 0) && (x < sideLength) && (y >= 0) && (y < sideLength)) {
			return board[x][y];
		}
		else {
			System.out.println("Error, invalid board position");
			return -2;
		}
	}
	
	/**
	 * 
	 */
	public void printBoard() {
		for(int y = 0; y < sideLength; y++ ) {
			for(int x = 0; x < sideLength; x++ ) {
				System.out.print(board[x][y] + " ");
			}
			System.out.println("");
		}
	}
	
	p
	
	/**
	 * Main method to test class.
	 * @param args
	 */
	public static void main(String [] args) {
		GameBoard test = new GameBoard(20);
		test.printBoard();
	}
	
	
	

}
