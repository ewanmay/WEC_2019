package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
	    setVisible(true);
	    	
    }
    
    /**
     * make the top panel containing the reset button 
     */
    private void makeResetButtonPanel() {
    		makeResetButton();
    		resetButtonPanel.add(resetButton);
		add(resetButtonPanel);
	}
    
    private void makeResetButton(){
		resetButton = new JButton("Reset");
        resetButton.setFocusPainted(false); // don't highlight as selected
    }


	/**
     * Creates the game board panel
     */
    public void makeGamePanel(){
        gamePanel.setLayout(new GridLayout(sideLength,3));
        gamePanel.setMinimumSize(new Dimension(700,700));
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
                buttons[i][j] = new JButton();
                buttons[i][j].setText("");
                buttons[i][j].setName(""+ i + ", "+ j);
                buttons[i][j].setPreferredSize(new Dimension(40, 40));
                buttons[i][j].setFocusPainted(false); // don't highlight selected buttons
                gamePanel.add(buttons[i][j]);
            }
        }      
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
     * @param col the col
     * @param text the button text
     * @param e the ActionListener
     */
    public void setButtonListener(int row, int col, ActionListener e) {
		buttons[row][col].addActionListener(e);
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
    public JButton[][] getButtons() {
    		return buttons;
    }
    
        
    //TODO main for testing. remove or comment out later
    //    public static void main(String[] args) {
    //		GameBoardView g = new GameBoardView(20);
    //	}
    
    
}
