package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.GameBoard;
import view.GameBoardView;

/**
 * Controller class that sets all button listeners
 */
public class GameBoardController {

	/**
	 * The view
	 */
	private GameBoardView gui;
	/**
	 * The board (model)
	 */
	private GameBoard board;
	/**
	 * The side length of the board, number of basins == sideLength^2
	 */
	private int sideLength;
	
	/**
	 * 
	 */
	private boolean isSim;
	
	
	/**
	 * Constructor to set the view, model, and sideLength, and sets button listeners
	 * @param gameView view of game
	 * @param boardModel model of board
	 * @param sideLength length of side
	 * @param sim the robot
	 */
	public GameBoardController(GameBoardView gameView, GameBoard boardModel, int sideLength, boolean sim) {
		board = boardModel;
		gui = gameView;
		this.sideLength = sideLength;
		isSim = sim;
		startGame();
	}

	/**
	 * Starts the game by setting all the button listeners
	 */
	private void startGame() {
		gui.setResetButtonListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});
		setGridButtonListeners();
	}


	/**
	 * reset the game
	 */
	public void reset(){
		if(isSim) {
			System.exit(1);
		}
		gui.dispose(); // close the old game gui
		
		StartScreen gameStarter = new StartScreen();
		sideLength = gameStarter.selectBoardSize();
		if(sideLength == -1) {
			System.out.println("No board size selected. Terminating...");
			System.exit(1);
		}
		board = new GameBoard(sideLength);
		gui = new GameBoardView(sideLength);
		startGame();
	}


	/**
	 * set the button listener for each button in the grid
	 */
	private void setGridButtonListeners() {
		for(int i = 0; i < sideLength; i++){
			for(int j = 0 ; j < sideLength ; j++){
				gui.setGridButtonListener(i, j, new GridButtonListener(this));
			}
		}
	}

	/**
	 * get the gui held by controller
	 * @return the gui
	 */
	public GameBoardView getGui() {
		return gui;
	}

	/**
	 * get the board from model
	 * @return the board used for game
	 */
	public GameBoard getBoard() {
		return board;
	}

}
