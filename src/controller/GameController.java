package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.GameBoard;
import view.GameBoardView;

public class GameController {
	GameBoardView GameView = null;
	GameBoard Board = null;
	
	private int sideLength;
	
	
	public GameController(GameBoardView gameView, GameBoard board) {
		super();
		GameView = gameView;
		Board = board;
		
		
	}
	
	public void startGame() {
		
	}
	
	public void receiveInput() {
		
	}
	
	private void setAllButtonListeners() {

        for(int i = 0; i < sideLength; i++)
        {
            for(int j = 0 ; j < sideLength ; j++){
            		GameView.setButtonListener(i, j, new ButtonListener(b));
            }
        }
	}
	
	/**
     * Inner listener class for the game buttons
     */
    class ButtonListener implements ActionListener{
        /**
         * set what happens upon clicking a button
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            int row=-1, col=-1;
            //find which button was clicked
            JButton buttonClicked = (JButton)e.getSource();
            JButton [][] buttons = GameView.getButtons();
            //TODO is this the best way to determine the row/col? get the entire button array and check all?
//            buttonClicked.setText(client.symbol); //set to player symbol
            //determine the row/col
            for (int i = 0 ; i < sideLength ; i++){
                for (int j = 0 ; j < sideLength ; j++){
                    if (buttonClicked==buttons[i][j]){
                        row=i;
                        col=j;
                    }
                }
            }
            
            //TODO process button click
            
            
        }
    }
	
	
}
