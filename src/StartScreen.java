


import javax.swing.JOptionPane;

public class StartScreen {
	
	StartScreen(){}
	
	public int SelectBoardSize() {
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
