package controller;


import javax.swing.JOptionPane;

/**
 * Popup to choose to play game or run simulator
 */

public class SelectSimulator {
	
	/**
	 * Default constructor for class SelectSimulator
	 */
	public SelectSimulator(){
	}
	
	/**
	 * method that allows users to select simulation mode or play mode
	 * @return boolean to see is user wants to simulate
	 */
	public boolean selectSimulateMode() {
		String[] inputButtons = {"Simulation", "Manual"};
		
		int result = JOptionPane.showOptionDialog(null, "Welcome!\nRun as Simulation?", "Lion Inc.", 
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, inputButtons, null);
		
		if(result == JOptionPane.YES_OPTION) {
			//sim
			return true;
		}
		else {
			return false;
		}
	}
	
}
