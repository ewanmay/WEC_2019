package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
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
     * panels on the JFram
     */
    private JPanel gamePanel = new JPanel(); // contains the board
    
    
    
    public GameBoardView(int sideLength) {
    	
    	buttons = new JButton[sideLength][sideLength];
    this.sideLength = sideLength;
    	
    	setTitle("Lion Inc."); //TODO change title?
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    makeGamePanel();

    pack();
    setVisible(true);
    	
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
//              buttons[i][j].addActionListener(new ButtonListener());
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
     */
    public void setButtonListener(int row, int col, ActionListener e) {
		buttons[row][col].addActionListener(e);
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
