package robot;

import java.util.ArrayList;
import javax.swing.JButton;
import view.GameBoardView;

/**
 * SimulatorController class allows this thread of the program to interact with the UI of the view. It does not have access to the back end class 'model'
 *
 */

public class SimulatorController {
	
	/**
	 * components that are currently displayed that we will have the simulator click on
	 */
	JButton[][] components;
	/**
	 * readyClicks is the queue that tells the simulator class what it can click on next
	 */
	ArrayList<JButton> readyClicks;
	/**
	 * basinBlocks arethe flagged blocks that the bot finds that it should not hit
	 */
	ArrayList<Coordinate> basinBlocks;
	/**
	 * this is the robot that will control the mouse of the users computer
	 */
	Simulator robot;
	
	/**
	 * Constructor for class SimulatorController that allows the gui to be controlled by a robot	
	 * @param gui the view class of the GameBoard controller that requires a bot to run it
	 */
	public SimulatorController(GameBoardView gui)
	{
		readyClicks = new ArrayList<JButton>();
		basinBlocks = new ArrayList<Coordinate>();
		robot = new Simulator(gui);
		components = robot.getButtonComponents();
	}
	
	/**
	 * This is the function that is called when you are ready to give up your mouse to the bot.
	 */
	public void controlGame() {
		robot.pressRandomButton(basinBlocks);
		while(!robot.getGUI().getGameLost() && !robot.getGUI().gameFinished()) {
			if(!nextClick()) {
				nextSpots();
				if(!nextClick())
					robot.pressRandomButton(basinBlocks);
			}
			
	
		}
		
	}
	
	/**
	 * refreshes the components to ensure most recent GUI has been displayed to not have thread conflicts
	 */
	public void refreshBoard()
	{
		components = robot.getButtonComponents();
	}
	
	/**
	 * nextSpots find the next available spot based on uncovered blocks that are not beside any Basins
	 */
	
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
				if(temp.getText().equals("1") && temp.getIcon() == null && !components[i][j].isEnabled() && readyClicks.size() > 0)
				{
					checkBlocksAroundOne(i, j);
				}
			}
		}
	}
	
	/**
	 * Finds the blocks around a "1" block to see if a mine can be isolated
	 * @param i x-coordinate of 
	 * @param j y-coordinate
	 */
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
							basinBlocks.add(new Coordinate(k,h));
							//fixSurroundingBlocks(k, h);
						}
					}
				}
			}
		}
	}
	
	/**
	 * checks the blocks surrounding a zero block and adds them all to the ready queue
	 * @param k x-coordinate
	 * @param h y-coordinate
	 */
	
	public void checkSurroundingBlocks(int k, int h)
	{
		if(k >= 0 && k < components.length && h >= 0 && h < components.length && components[k][h].isEnabled() && notInList(components[k][h]))
		{
			readyClicks.add(components[k][h]);
		}
	}
	
	/**
	 * attempts to fix the blocks surrounding a Basin to see if their other blocks can be clicked
	 * @param k x-coordinate of Basin
	 * @param h y-coordinate of Basin
	 */
	
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
	
	/**
	 * checks the Basins' surrounding blocks to try and click them
	 * @param i x-coordinate
	 * @param j y-coordinate
	 * @param badI x-coordinate of Basin
	 * @param badJ y-coordinate of Basin
	 */
	
	public void surroundingBlockCheck(int i, int j, int badI, int badJ)
	{
		// Check how many blocks are available around block
		if(components[i][j].getText().equals("1")) {
				int numAvailable  = 0;
				Boolean validBlockToClick = false;
				for(int k = i - 1; k < i + 2 && k >= 0 && k < components.length; k++)
				{
					for(int h = j - 1; h < j + 2 && h < components.length && h >= 0; h++)
					{
						if(components[k][h].isEnabled())
						{
							numAvailable++;
						}
						if(k == badI && h == badJ) {
							validBlockToClick = true;
							//numAvailable--;
						}
					}
				}
				if((numAvailable > 1) && validBlockToClick)
				{
					// This blocks surrounding blocks are good to click
					for(int k = i - 1; k < i + 2 && k >= 0 && k < components.length; k++)
					{
						for(int h = j - 1; h < j + 2 && h < components.length && h >= 0; h++)
						{
							if(k != badI && h != badJ && components[k][h].isEnabled()) 
							{
								robot.mouseMoveAndClick(components[k][h]);
							}
						}
					}
				}
		}
	}
	
	/**
	 * checks to see if a given button is in the readyClicks list already
	 * @param button that you want to check
	 * @return true if not in list, false otherwise
	 */
	
	private boolean notInList(JButton button) {
		for(int i = 0; i<readyClicks.size(); i++) {
			if(button == readyClicks.get(i))
				return false;
		}
		return true;
	}
	
	/**
	 * clicks the next available block to clear of snow
	 * @return true if successful, false otherwise
	 */

	public boolean nextClick() {
		if(readyClicks.size() > 0) {
			robot.mouseMoveAndClick(readyClicks.remove(0));
			return true;
		}
		else
			return false;
	}

}
