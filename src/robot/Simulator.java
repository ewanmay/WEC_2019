package robot;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Random;
import javax.swing.JButton;
import view.GameBoardView;

/*
 * Simulates running the program automatically.
 */
public class Simulator {

	/**
	 * Robot object that can click buttons automatically
	 */
	private Robot robot;
	
	/**
	 * The GUI to control
	 */
	private GameBoardView gui;
	
	/**
	 * The buttons to control
	 */
	private JButton [][] buttonComponents;
	
	/**
	 * the reset button to control
	 */
	private JButton resetButton;
	
	/**
	 * Ctor to init the Robot, given theGUI to control
	 * @param theGui
	 */
	public Simulator(GameBoardView theGui) {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		gui = theGui;
		
		while (!gui.isShowing()) {
			//wait for GUI to load
		}
		
		Component components1 [] = gui.getResetButtonPanel().getComponents();
		resetButton = (JButton)components1[0];
		
		repopulateButtons();
		robot.delay(1000);
	}
	
	/**
	 * update the local state of the buttons, called after every click
	 */
	private void repopulateButtons() {
		Component components2 [] = gui.getGamePanel().getComponents();
		
		buttonComponents = new JButton[(int) Math.sqrt(components2.length)][(int) Math.sqrt(components2.length)];
		
		for(int j=0, k=0; j < buttonComponents.length; j++) {
			for(int i=0; i < buttonComponents[j].length; i++, k++) {
				buttonComponents[i][j] = (JButton)components2[k];
			}
		}
	}
	
	public JButton[][] getButtonComponents()
	{
		return buttonComponents;
	}
	
	/**
	 * This function was made using the following source.
	 * https://www.developer.com/java/other/article.php/2241561/An-Automated-Test-Program-using-the-Java-Robot-Class.htm
	 * @param xLoc
	 * @param yLoc
	 */
	public void mouseMoveAndClick(Component c){
		Point location = c.getLocationOnScreen();
		
		int xLoc, yLoc;
		do {
		robot.mouseMove( location.x + 20, location.y + 20 );
		robot.delay(100);
		xLoc = MouseInfo.getPointerInfo().getLocation().x;
		yLoc = MouseInfo.getPointerInfo().getLocation().y;
		}while( Math.abs(location.x - xLoc + 20) > 10
				|| Math.abs(location.y - yLoc + 20) > 10);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);

	}
	
	/**
	 * select a random button and click it
	 */
	public void pressRandomButton() {
		Random r = new Random();
		int xPos =  r.nextInt((buttonComponents.length - 1 - 0) + 1) + 0;
		r = new Random();
		int yPos =  r.nextInt((buttonComponents.length - 1 - 0) + 1) + 0;
		mouseMoveAndClick(buttonComponents[xPos][yPos]);
		//repopulateButtons();
	}
	
	/**
	 * Press a button, given its coordinates in the 2D array
	 * @param xPos
	 * @param yPos
	 */
	public void pressButton(int xPos, int yPos) {
		mouseMoveAndClick(buttonComponents[xPos][yPos]);
		//repopulateButtons();
	}
	
	/**
	 * returns the gui that is needed to know if bot has won
	 * @return gui that bot is trying to solve
	 */
	public GameBoardView getGUI()
	{
		return gui;
	}

}
