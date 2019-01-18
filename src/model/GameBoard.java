package model;


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
	 */
	public void printBoard() {
		for(int y = 0; y < sideLength; y++ ) {
			for(int x = 0; x < sideLength; x++ ) {
				System.out.print(board[x][y] + " ");
			}
			System.out.println("");
		}
	}
	
//	public static void main(String [] args) {
//		GameBoard test = new GameBoard(20);
//		test.printBoard();
//	}
	
	
	

}
