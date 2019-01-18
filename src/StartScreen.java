


import javax.swing.JOptionPane;

/**
 * StartScreen class that displays the initial screen that allows you to decide what board size you want
 *
 */

public class StartScreen {
	
	/**
	 * Default constructor for class StartScreen
	 */
	
	public StartScreen(){
	}
	
	/**
	 * function that allows users to select the size of the game board they would like to play
	 * @return the size of the board selected represented as an Integer type
	 */
	public int selectBoardSize() {
		String[] inputButtons = {"Small", "Medium", "Large"};
		
		int result = JOptionPane.showOptionDialog(null, "Select Size of Board", "Select size of board", 
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, inputButtons, null);
		
		if(result == JOptionPane.YES_OPTION) 
		{
			return 10;
		}
		else if(result == JOptionPane.NO_OPTION) 
		{
			return 20;
		}
		else if(result == JOptionPane.CANCEL_OPTION) 
		{
			return 30;
		}
		else
		{
			return -1;
		}
	}
	
}
