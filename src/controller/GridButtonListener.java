package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class GridButtonListener implements ActionListener{

	/**
	 * the controller that can access the gui and model
	 */
	private GameBoardController controller;
	
	/**
	 * Constructor to init the controller
	 * @param g
	 * @param b
	 * @param con
	 */
	public GridButtonListener(GameBoardController con) {
		controller = con;
	}

	/**
	 * set the action performed upon button click
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int row = -1, col = -1;
		//find which button was clicked
		JButton buttonClicked = (JButton)e.getSource();

		String[] buttonName = buttonClicked.getName().split(", ");
		try {
			row = Integer.parseInt(buttonName[0]);
			col = Integer.parseInt(buttonName[1]);
		}catch (NumberFormatException error) {
			System.err.println("ERROR parsing button name to get row/col.. Terminating..");
			System.exit(1);
		}

		if ((e.getModifiers() & ActionEvent.CTRL_MASK) == ActionEvent.CTRL_MASK) {
			//CTRL CLICK - place flag
			if(buttonClicked.getIcon() == null) {
				setIconWithPath(buttonClicked, "res" + "/" + "flag.png");  
			}
			else {
				buttonClicked.setIcon(null);
				controller.getGui().repaint();
			}
		} 
		else {
			//NORMAL LEFT CLICK
			if(buttonClicked.getIcon() == null) { //ensure space is not flagged
				setButtonAppearance(buttonClicked, row, col);
				controller.getBoard().incrementClearedSpaces();
				controller.getGui().incrementClearedSpaces(); // needed in the GUI for the simulator
				if(controller.getBoard().checkWin()){
					//game over, all spaces cleared
					System.out.println("All Cleared");
					JOptionPane.showMessageDialog(null, "All Spaces Cleared!", "Lion Inc.", JOptionPane.PLAIN_MESSAGE);
					controller.reset();
					return;
				}
			}
		}


	}


	private void setIconWithPath(JButton buttonClicked, String path) {
		ImageIcon imageIcon = new ImageIcon(path);
		Image image = imageIcon.getImage();
		Image newImage = image.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(newImage);
		buttonClicked.setIcon(imageIcon);
		controller.getGui().repaint();
	}

	/**
	 * Change the button text/icon based on number of adjacent basins
	 * @param button the button to change
	 * @param row the x Coordinate
	 * @param col the y Coordinate
	 */
	private void setButtonAppearance(JButton button, int row, int col) {
		//set text (number of adjacent basins)
		int buttonDisplay = controller.getBoard().boardAt(row, col);
		if(buttonDisplay == -1) {
			//a basin was hit
			setIconWithPath(button, "res" + "/" + "square.png");  
			controller.getGui().setGameLost(true);
			JOptionPane.showMessageDialog(null, "Basin Hit!", "Lion Inc.", JOptionPane.ERROR_MESSAGE);
			controller.reset();
			return;
		}
		else if(buttonDisplay == 0) {
			//empty space with no adjacent basins
			button.setText("");
			button.setOpaque(true);
			button.setBackground(Color.DARK_GRAY); 
		}
		else {
			setButtonColor(button, buttonDisplay);
			button.setText(""+buttonDisplay);
			int buttonHeight = button.getSize().height;
			System.out.println(buttonHeight);
			button.setFont(new Font("Arial", Font.PLAIN, Math.floorDiv(buttonHeight, 2)));
			button.repaint();
		}
		button.setEnabled(false); //disable button
	}

	/**
	 * set the background color of a button
	 * @param button
	 * @param buttonDisplay integer representing which color to set 
	 */
	private void setButtonColor(JButton button, int buttonDisplay) {
		System.out.println("set button color: "+buttonDisplay);
		button.setOpaque(true);

		switch(buttonDisplay) {
		case 1:
			button.setBackground(Color.CYAN);
			break;
		case 2:
			button.setBackground(Color.GREEN);
			break;
		case 3:
			button.setBackground(Color.RED);
			break;
		case 4:
			button.setBackground(Color.MAGENTA);
			break;
		case 5:
			button.setBackground(Color.ORANGE);
			break;
		case 6:
			button.setBackground(Color.CYAN);
			break;
		case 7:
			button.setBackground(Color.YELLOW);
			break;
		case 8:
			button.setBackground(Color.PINK);
			break;
		default: 
			break;
		}
	}


}//end of class ButtonListener
