package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import model.GameBoard;
import view.GameBoardView;

/**
 * Controller class that sets all button listeners
 */
public class GameBoardController {
	/**
	 * The view
	 */
	private GameBoardView gui = null;
	/**
	 * The board (model)
	 */
	private GameBoard board = null;
	/**
	 * The side length of the board, number of basins == sideLength^2
	 */
	private int sideLength;
	
	/**
	 * Constructor to set the view, model, and sideLength, and sets button listeners
	 * @param gameView
	 * @param boardModel
	 * @param sideLength
	 */
	public GameBoardController(GameBoardView gameView, GameBoard boardModel, int sideLength) {
		board = boardModel;
		gui = gameView;
		this.sideLength = sideLength;
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
		gui.dispose(); // close the old game gui
		StartScreen gameStarter = new StartScreen();
		sideLength = gameStarter.selectBoardSize();
		if(sideLength == -1) {
			System.err.println("Error selecting board size.");
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
	                gui.setGridButtonListener(i, j, new ButtonListener());
	            }
	        }
	}
	
	/**
     * Inner listener class for the game buttons
     */
    class ButtonListener implements ActionListener{
        /**
         * set the action performed upon button click
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = -1, col = -1;
            //find which button was clicked
            JButton buttonClicked = (JButton)e.getSource();
            JButton [][] buttons = gui.getGridButtons();
            String[] buttonName = buttonClicked.getName().split(", ");
            try {
	            row = Integer.parseInt(buttonName[0]);
	            col = Integer.parseInt(buttonName[1]);
            }catch (NumberFormatException error) {
				System.err.println("ERROR parsing button name to get row/col.. Terminating..");
				System.exit(1);
            }
            setButtonAppearance(buttonClicked, row, col);
        }
        
        private void setButtonAppearance(JButton button, int row, int col) {
            //set text (number of adjacent basins)
            int buttonDisplay = board.boardAt(row, col);
            if(buttonDisplay == -1) {
            		button.setIcon(new ImageIcon("/res/square.png"));  
            		JOptionPane.showMessageDialog(null, "Basin Hit!", "Lion Inc.", JOptionPane.ERROR_MESSAGE);
            		reset();
            }
            else if(buttonDisplay == 0) {
            		//empty space with no adjacent basins
            		button.setText("");
            }
            else {
            		//show num of adjacent basins
            		//TODO color of text display
        			button.setText(""+buttonDisplay);
            }
            board.incrementClearedSpaces();
            button.setEnabled(false); //disable button
            if(board.checkWin()){
            		//game over, all spaces cleared
            		System.out.println("All Cleared");
	            	JOptionPane.showMessageDialog(null, "All Spaces Cleared!", "Lion Inc.", JOptionPane.PLAIN_MESSAGE);
	        		reset();
            }
        }
    }
	
}
