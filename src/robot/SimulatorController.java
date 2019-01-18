package robot;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JButton;

public class SimulatorController {
	
	ArrayList<Coordinate> readyClicks;
	
	SimulatorController()
	{
		readyClicks = new ArrayList<Coordinate>();
	}
	
	public void nextSpots(Component[][] components)
	{
		
		for(int i = 0; i < components.length; i++)
		{
			for(int j = 0; j < components[i].length; i++)
			{
				JButton temp = (JButton) components[i][j];
				if(temp.getText().equals("0"))
				{
					for(int k = i-1; k < i+2; k+=2)
					{
						for(int h = j-1; h < j+2; h+=2)
						{
							if((k >= 0) && k < components.length && h >= 0 && h < components.length)
							{
								readyClicks.add(new Coordinate(k, h));
							}
						}
					}
				}
			}
		}
		
		return null;
	}
	
	public Coordinate 

}
