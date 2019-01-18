package robot;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JButton;

import view.GameBoardView;

public class SimulatorController {
	
	GameBoardView gui;
	JButton[][] components;
	ArrayList<Coordinate> readyClicks;
	Simulator robot;
	
	SimulatorController(GameBoardView gui)
	{
		this.gui = gui;
		readyClicks = new ArrayList<Coordinate>();
		robot = new Simulator(gui);
		components = robot.getButtonComponents();
	}
	
	public void refreshBoard()
	{
		components = robot.getButtonComponents();
	}
	
	public void nextSpots()
	{
		
		for(int i = 0; i < components.length; i++)
		{
			for(int j = 0; j < components[i].length; i++)
			{
				JButton temp = components[i][j];
				if(temp.getText().equals("0"))
				{
					for(int k = i-1; k < i+2; k+=2)
					{
						for(int h = j-1; h < j+2; h+=2)
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
		if((components[k][h]).isEnabled())
		{
			if((k >= 0) && k < components.length && h >= 0 && h < components.length)
			{
				readyClicks.add(new Coordinate(k, h));
			}
		}
	}
	
	public Coordinate nextClick() {
		if(readyClicks.size() > 0)
			return readyClicks.remove(0);
		else
			return null;
	}

}
