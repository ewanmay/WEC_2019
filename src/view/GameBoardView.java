package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * the view class showing the GUI. extends JFrame
 * 
 */
@SuppressWarnings("serial")
public class GameBoardView extends JFrame{

	/**
	 * the buttons of the game board
	 */
	private JButton [][] buttons;

	/**
	 * sideLength of gameBoard. Number of Buttons = sideLength^2
	 */
	private int sideLength;
	/**
	 * the number of cleared spaces on the grid
	 */
	private int clearedSpaces = 0;
	/**
	 * variable to know a mine has been hit
	 */
	private Boolean gameLost = false;
	/**
	 * reset button
	 */
	private JButton resetButton;


	/**
	 * Contains the board (button grid)
	 */
	private JPanel gamePanel = new JPanel(); // contains the board
	/**
	 * panel that contains reset button
	 */
	private JPanel resetButtonPanel = new JPanel(); // reset button, text info



	public GameBoardView(int sideLength) {

		buttons = new JButton[sideLength][sideLength];
		this.sideLength = sideLength;

		setTitle("Lion Inc.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

		makeResetButtonPanel();
		makeGamePanel();
		pack();
		if(sideLength>=20) {
			//auto maximize if game is MEDIUM or LARGE
			setExtendedState(JFrame.MAXIMIZED_BOTH);
		}
		setLocationRelativeTo(null); // show in center of screen
		setVisible(true);
	}

	/**
	 * make the top panel containing the reset button 
	 */
	private void makeResetButtonPanel() {
		makeResetButton();
		resetButtonPanel.add(resetButton);
		resetButtonPanel.setMaximumSize(resetButton.getPreferredSize()); // so this panel doesn't grow
		add(resetButtonPanel);
	}

	/**
	 * init the reset button
	 */
	private void makeResetButton(){
		resetButton = new JButton("Reset");
		resetButton.setFocusPainted(false); // don't highlight as selected
	}


	/**
	 * Creates the game board panel
	 */
	public void makeGamePanel(){
		gamePanel.setLayout(new GridLayout(sideLength,sideLength));
//		gamePanel.setMaximumSize(new Dimension(sideLength*44,sideLength*30));
		gamePanel.setMinimumSize(new Dimension(sideLength*10,sideLength*10));
		
		
		initializeButtons();
		gamePanel.setBorder(BorderFactory.createRaisedBevelBorder());
		add(gamePanel);
	}

	/**
	 * init all the game buttons and add them to the gamePanel
	 */
	private void initializeButtons(){
		for(int i = 0; i < sideLength; i++)
		{
			for(int j = 0 ; j < sideLength ; j++){
				JButton b = new JButton();
				b.setText("");
				b.setName(""+ i + ", "+ j); //for IDing upon click
				b.setPreferredSize(new Dimension(80,80)); // 80,80
				b.setFocusPainted(false); // don't highlight selected buttons
				b.setFont(new Font("Arial", Font.PLAIN, 11));
				buttons[i][j] = b;
				gamePanel.add(b);
			}
		}      
	}

	/**
	 * increment clearedSpaces by 1
	 */
	public void incrementClearedSpaces() {
		clearedSpaces++;
	}
	
	/**
	 * check if user has won
	 */
	public Boolean gameFinished() {
		if(clearedSpaces == sideLength*sideLength - sideLength)
		{
			return true;
		}
		else
			return false;
	}

	/**
	 * sets the button text for the JButton located at [row][col]
	 * @param row the row
	 * @param col the col
	 * @param text the button text
	 */
	public void setButtonText(int row, int col, String text) {
		buttons[row][col].setText(text);
	}

	/**
	 * adds action listener for the specified JButton located at [row][col]
	 * @param row the row
	 * @param col the Column
	 * @param text the button text to display
	 * @param e the ActionListener
	 */
	public void setGridButtonListener(int row, int col, ActionListener e) {
		buttons[row][col].addActionListener(e);
//		buttons[row][col].addMouseListener(e);
	}

	/**
	 * adds action listener for the reset button
	 * @param e the ActionListener
	 */
	public void setResetButtonListener(ActionListener e) {
		resetButton.addActionListener(e);
	}

	/**
	 * @return the 2D array of JButtons
	 */
	public JButton[][] getGridButtons() {
		return buttons;
	}
	
	/**
	 * @return the gamePanel
	 */
	public JPanel getGamePanel(){
		return gamePanel;
	}

	/**
	 * @return the resetButtonPanel
	 */
	public JPanel getResetButtonPanel(){
		return resetButtonPanel;
	}
	
	/**
	 * sets the variable that lets users know if they have lost the game
	 * @param b is the new gameLost variable
	 */
	public void setGameLost(Boolean b)
	{
		gameLost = b;
	}
	
	/**
	 * @return the gameLost
	 */
	public Boolean getGameLost() {
		return gameLost;
	}
	
    
    
}
