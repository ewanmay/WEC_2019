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

public class Simulator {

	Robot robot;
	
	GameBoardView gui;
	
	JButton [][] buttonComponents;
	
	JButton resetButton;
	
	public Simulator(GameBoardView theGui) {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		gui = theGui;
		
		while (!gui.isShowing()) {
		}
		
		Component components1 [] = gui.getResetButtonPanel().getComponents();
		resetButton = (JButton)components1[0];
		
		repopulateButtons();
		
	}
	
	private void repopulateButtons() {
		Component components2 [] = gui.getGamePanel().getComponents();
		
		buttonComponents = new JButton[(int) Math.sqrt(components2.length)][(int) Math.sqrt(components2.length)];
		
		for(int j=0, k=0; j < buttonComponents.length; j++) {
			for(int i=0; i < buttonComponents[j].length; i++, k++) {
				buttonComponents[i][j] = (JButton)components2[k];
			}
		}
	}
	
	/**
	 * From: https://www.developer.com/java/other/article.php/2241561/An-Automated-Test-Program-using-the-Java-Robot-Class.htm
	 * @param xLoc
	 * @param yLoc
	 */
	public void mouseMoveAndClick(Component c){
		Point location = c.getLocationOnScreen();
		do {
		robot.mouseMove( location.x + 20, location.y + 20 );
		}while(MouseInfo.getPointerInfo().getLocation().x != location.x + 20
				&& MouseInfo.getPointerInfo().getLocation().y != location.y + 20);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
		robot.delay(1500);
	}
	
	public void pressRandomButton() {
		Random r = new Random();
		int xPos =  r.nextInt((buttonComponents.length - 1 - 0) + 1) + 0;
		r = new Random();
		int yPos =  r.nextInt((buttonComponents.length - 1 - 0) + 1) + 0;
		mouseMoveAndClick(buttonComponents[xPos][yPos]);
		repopulateButtons();
	}
	
	public void pressButton(int xPos, int yPos) {
		mouseMoveAndClick(buttonComponents[xPos][yPos]);
		repopulateButtons();
	}
	
	
	public static void main(String[] args) {
		Simulator test = new Simulator(new GameBoardView(10));
		for(int i=0 ; i<10; i++) {
			test.pressRandomButton();
		}
	}

}
