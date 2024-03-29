package robot;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import view.GameBoardView;

/**
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
	 * Ctor to init the Robot, given theGUI to control
	 * @param theGui gui that will  be displayed
	 */
	public Simulator(GameBoardView theGui) {
		//NOTE: system preferences may need to be modified to grant Eclipse
		// accessibilty access to control the mouse 
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		gui = theGui;
		
		while (!gui.isShowing()) {
			//wait for GUI to show
		}
		
		
		Component components [] = gui.getGamePanel().getComponents();
		
		buttonComponents = new JButton[(int) Math.sqrt(components.length)][(int) Math.sqrt(components.length)];
		
		for(int j=0, k=0; j < buttonComponents.length; j++) {
			for(int i=0; i < buttonComponents[j].length; i++, k++) {
				buttonComponents[i][j] = (JButton)components[k];
			}
		}
		
		robot.delay(1000);
	}
	
	
	public JButton[][] getButtonComponents()
	{
		return buttonComponents;
	}
	
	/**
	 * This function was made using the following source.
	 * https://www.developer.com/java/other/article.php/2241561/An-Automated-Test-Program-using-the-Java-Robot-Class.htm\
	 * @param c is the component to click on
	 */
	public void mouseMoveAndClick(Component c){
		Point location = c.getLocationOnScreen();
		
		int xLoc, yLoc;
		do {
		robot.mouseMove( location.x + 20, location.y + 20 );
		robot.delay(50);
		xLoc = MouseInfo.getPointerInfo().getLocation().x;
		yLoc = MouseInfo.getPointerInfo().getLocation().y;
		}while( Math.abs(location.x - xLoc + 20) > 10
				|| Math.abs(location.y - yLoc + 20) > 10);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(50);

	}
	
	/**
	 * select a random button and click it
	 * @param badButtons to click
	 */
	public void pressRandomButton(ArrayList<Coordinate> badButtons) {
		int xPos;
		int yPos;
		do {
			Random r = new Random();
			xPos =  r.nextInt((buttonComponents.length - 1 - 0) + 1) + 0;
			r = new Random();
			yPos =  r.nextInt((buttonComponents.length - 1 - 0) + 1) + 0;
		}while(!buttonComponents[xPos][yPos].isEnabled() || !goodRandomNumber(badButtons, xPos, yPos) );
		mouseMoveAndClick(buttonComponents[xPos][yPos]);
	}
	
	/**
	 * checkArrayList for good numbers to randomly hit
	 * @param badButtons to check
	 * @param xPos for x
	 * @param yPos for y
	 * @return whether number is good
	 */
	public Boolean goodRandomNumber(ArrayList<Coordinate> badButtons, int xPos, int yPos)
	{
		Boolean good = true;
		for(int i = 0; i < badButtons.size(); i++)
		{
			if(badButtons.get(i).getX() == xPos && badButtons.get(i).getY() == yPos)
				good = false;
		}
		return good;
	}
	
	/**
	 * Press a button, given its coordinates in the 2D array
	 * @param xPos x-coordinate
	 * @param yPos y-coordinate
	 */
	public void pressButton(int xPos, int yPos) {
		mouseMoveAndClick(buttonComponents[xPos][yPos]);
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
