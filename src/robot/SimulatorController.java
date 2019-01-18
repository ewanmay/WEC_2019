package robot;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JButton;

import view.GameBoardView;

public class SimulatorController {
	
	JButton[][] components;
	ArrayList<JButton> readyClicks;
	Simulator robot;
	
		
	public SimulatorController(GameBoardView gui)
	{
		readyClicks = new ArrayList<JButton>();
		robot = new Simulator(gui);
		components = robot.getButtonComponents();
	}
	
	public void controlGame() {
		robot.pressRandomButton();
		while(true) {
			if(!nextClick()) {
				nextSpots();
				if(!nextClick())
					robot.pressRandomButton();
			}
			
	
		}
		
	}
	
	public void refreshBoard()
	{
		components = robot.getButtonComponents();
	}
	
	public void nextSpots()
	{
		
		for(int i = 0; i < components.length; i++)
		{
			for(int j = 0; j < components[i].length; j++)
			{
				JButton temp = components[i][j];
				if(temp.getText().equals("") && temp.getIcon() == null && !components[i][j].isEnabled())
				{
					for(int k = i-1; k < i+2; k++)
					{
						for(int h = j-1; h < j+2; h++)
						{
							checkSurroundingBlocks(k, h);
						}
					}
				}
			}
		}
	}
	
	public void checkSurroundingBlocks(int k, int h)
	{
		if(k >= 0 && k < components.length && h >= 0 && h < components.length && components[k][h].isEnabled() && notInList(components[k][h]))
		{
			readyClicks.add(components[k][h]);
		}
	}
	
	private boolean notInList(JButton button) {
		for(int i = 0; i<readyClicks.size(); i++) {
			if(button == readyClicks.get(i))
				return false;
		}
		return true;
	}

	public boolean nextClick() {
		if(readyClicks.size() > 0) {
			robot.mouseMoveAndClick(readyClicks.remove(0));
			return true;
		}
		else
			return false;
	}

}
