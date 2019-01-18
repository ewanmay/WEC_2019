package robot;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.Random;

import view.GameBoardView;

public class Simulator {

	Robot robot;
	
	GameBoardView gui;
	
	Component [][] buttonComponents;
	
	Component resetButton;
	
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
		//System.out.println(components1.length);
		resetButton = components1[0];
		
		
		Component components2 [] = gui.getGamePanel().getComponents();
		//System.out.println(components2.length);	
		
		buttonComponents = new Component[(int) Math.sqrt(components2.length)][(int) Math.sqrt(components2.length)];
		
		for(int j=0, k=0; j < buttonComponents.length; j++) {
			for(int i=0; i < buttonComponents[j].length; i++, k++) {
				buttonComponents[i][j] = components2[k];
				//Point location =buttonComponents[i][j].getLocationOnScreen();
				//System.out.println(location);
				//mouseMoveAndClick(buttonComponents[i][j]);
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
		
	}
	
	public void pressButton(int xPos, int yPos) {
		mouseMoveAndClick(buttonComponents[xPos][yPos]);
	}
	
	
	public static void main(String[] args) {
		Simulator test = new Simulator(new GameBoardView(10));
		for(int i=0 ; i<10; i++) {
			test.pressRandomButton();
		}
	}

}
