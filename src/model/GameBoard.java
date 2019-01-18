package model;

import java.util.Random;


/**
 * This class represents the game board, 
 * it has a 2 d integer array that represents the game board.
 */
public class GameBoard {
	/**
	 * The length of one side of the board
	 */
	private int sideLength;
	
	/**
	 * The representation of the game board.
	 * Contains -1 for a mine or the number of mines adjacent to the tile.
	 */
	private int board[][];
	
	/**
	 * Constructs the game board.
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
	 * Returns the vale in the board array at a given index.
	  * @param x horizontal position.
	 * @param y vertical position.
	 * @return the value at that position.
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
	 * Method that prints the board to the console.
	 */
	public void printBoard() {
		for(int y = 0; y < sideLength; y++ ) {
			for(int x = 0; x < sideLength; x++ ) {
				System.out.print(board[x][y] + " ");
			}
			System.out.println("");
		}
	}
	

	/**
	 * Main method to test class.
	 * @param args command line arguments.
	 */
//	public static void main(String [] args) {
//		GameBoard test = new GameBoard(10);
//		test.printBoard();
//	}

	
	
	

}
