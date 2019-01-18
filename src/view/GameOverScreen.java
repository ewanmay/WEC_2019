package view;

import javax.swing.JOptionPane;

public class GameOverScreen {
	
	public Boolean gameOver(String message) {
		
		String[] playAgainOptions = {"Yes", "No"};
		
		int result = JOptionPane.showOptionDialog(null, message + "\nWould you like to play again?", "Game over", 
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, playAgainOptions, null);
		
		if(result == JOptionPane.YES_OPTION)
		{
			return true;
		}
		
		return false;
	}

}
