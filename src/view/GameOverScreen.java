package view;

import javax.swing.JOptionPane;
/**
 * GameOverScreen.java allows the selection of starting a game over again after completion or failure of the current game.
 *
 */

public class GameOverScreen {
	
	/**
	 * Takes in a message that tells the user if they won or lost the game. 
	 * @param message if the user has  won or lost
	 * @return true if user would like to play again, false if user does not
	 */
	
	public Boolean gameOver(String message) {
		
		String[] playAgainOptions = {"Yes", "No"};
		
		int result = JOptionPane.showOptionDialog(null, message + "\nWould you like to play again?", "Game over", 
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, playAgainOptions, null);
		
		if(result == JOptionPane.YES_OPTION){
			return true;
		}
		
		return false;
	}

}
