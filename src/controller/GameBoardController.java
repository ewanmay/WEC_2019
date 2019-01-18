package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import model.GameBoard;
import view.GameBoardView;

public class GameBoardController {
	private GameBoardView gui = null;
	private GameBoard board = null;
	private int sideLength;
	
	public GameBoardController(GameBoardView gameView, GameBoard boardModel, int sideLength) {
		board = boardModel;
		gui = gameView;
		this.sideLength = sideLength;
		setAllButtonListeners();
	}
	
	public void startGame() {
	}
	
	public void receiveInput() {
		
	}
	/**
	 * set the button listener for each button in the grid
	 */
	private void setAllButtonListeners() {
		 for(int i = 0; i < sideLength; i++){
	            for(int j = 0 ; j < sideLength ; j++){
	                gui.setButtonListener(i, j, new ButtonListener());
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
            JButton [][] buttons = gui.getButtons();
            String[] buttonName = buttonClicked.getName().split(", ");
            row = Integer.parseInt(buttonName[0]);
            col = Integer.parseInt(buttonName[1]);

            //TODO process button clicks
            //set text
            buttonClicked.setText("T"); // TODO test text
            //disable button
            buttonClicked.setEnabled(false);
            int buttonValue = board.boardAt(x, y)
        }
    }
	
}
