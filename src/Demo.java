import controller.GameBoardController;
import model.GameBoard;
import view.GameBoardView;

public class Demo {

	public static void main(String[] args) {
		
		StartScreen gameStarter = new StartScreen();

		GameBoard model = new GameBoard();
		GameBoardView view = new GameBoardView(gameStarter.SelectBoardSize());
		
		GameBoardController controller = new GameBoardController(view);
		
		

	}

}
