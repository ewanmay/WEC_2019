package robot;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Robot;

import view.GameBoardView;

public class Simuator {

	Robot robot;
	
	GameBoardView gui;
	
	Component [][] buttonComponents;
	
	Component resetButton;
	
	public Simuator(GameBoardView theGui) {
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		gui = theGui;
		
		while (!gui.isShowing()) {
		}
		
		
		Component[] components = gui.getContentPane().getComponents();
		
		resetButton = components[0];
		
		
		buttonComponents = new Component[(components.length - 1)/3][(components.length - 1)/3];
		
		for(int j=0, k=1; j < buttonComponents.length; j++) {
			for(int i=0; i < buttonComponents[j].length; i++, k++) {
				buttonComponents[i][j] = components[k];
				System.out.println(components[k].getLocation());
			}
		}
		
		
		
	}
	
	
	public void pressRandomButton() {
		
	}
	
	
	public static void main(String[] args) {
		Simuator test = new Simuator(new GameBoardView(10));
	}

}
