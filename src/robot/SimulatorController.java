package robot;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JButton;

import view.GameBoardView;

public class SimulatorController {
	
	JButton[][] components;
	ArrayList<JButton> readyClicks;
	ArrayList<JButton> basinBlocks;
	Simulator robot;
	
		
	public SimulatorController(GameBoardView gui)
	{
		readyClicks = new ArrayList<JButton>();
		basinBlocks = new ArrayList<JButton>();
		robot = new Simulator(gui);
		components = robot.getButtonComponents();
	}
	
	public void controlGame() {
		robot.pressRandomButton();
		while(!robot.getGUI().getGameLost() && !robot.getGUI().gameFinished()) {
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
				if(temp.getText().equals("1") && temp.getIcon() == null && !components[i][j].isEnabled())
				{
					checkBlocksAroundOne(i, j);
				}
			}
		}
	}
	
	public void checkBlocksAroundOne(int i, int j)
	{
		int availableBlocksCounter = 0;
		for(int k = i - 1; k < i+2; k++)
		{
			for(int h = j-1; h < j+2; h++)
			{
				if(k != i && j != h && k >=0 && h >= 0 && k < components.length && h < components.length)	// don't want to include the same block
				{
					if(components[k][h].isEnabled())
						availableBlocksCounter++;
				}
			}
		}
		if(availableBlocksCounter == 1)
		{
			// search for the bad block
			for(int k = i - 1; k < i+2; k++)
			{
				for(int h = j-1; h < j+2; h++)
				{
					if(k != i && j != h && k >=0 && h >= 0 && k < components.length && h < components.length)	// don't want to include the same block
					{
						if(components[k][h].isEnabled())
						{
							basinBlocks.add(components[k][h]);
							fixSurroundingBlocks(k, h);
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
	
	public void fixSurroundingBlocks(int k, int h)
	{
		for(int i = k - 1; i < k+2 && i >= 0 && i < components.length; i++)
		{
			for(int j = h - 1; j < h + 2 && j >= 0 && j < components.length; j++)
			{
				if(k != i && j != h && components[i][j].getText().equals("1"))
				{
					surroundingBlockCheck(i, j, k, h);
				}
			}
		}
	}
	
	public void surroundingBlockCheck(int i, int j, int badI, int badJ)
	{
		// Check how many blocks are available around block
		int numAvailable  = 0;
		for(int k = i - 1; k < i + 2 && k >= 0 && k < components.length; k++)
		{
			for(int h = j - 1; h < i + 2 && h < components.length && h >= 0; h++)
			{
				if(components[k][h].isEnabled())
				{
					numAvailable++;
				}
			}
		}
		if(numAvailable > 1)
		{
			// This blocks surrounding blocks are good to click
			for(int k = i - 1; k < i + 2 && k >= 0 && k < components.length; k++)
			{
				for(int h = j - 1; h < i + 2 && h < components.length && h >= 0; h++)
				{
					if(k != badI && h != badJ && components[k][h].isEnabled()) 
					{
						robot.mouseMoveAndClick(components[k][h]);
					}
				}
			}
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
