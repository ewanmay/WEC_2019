import controller.GameBoardController;
import model.GameBoard;
import view.GameBoardView;

public class Demo {

	public static void main(String[] args) {
		
		StartScreen gameStarter = new StartScreen();
		int boardLength = gameStarter.selectBoardSize();

		GameBoard model = new GameBoard(boardLength);
		GameBoardView view = new GameBoardView(boardLength);
		
		GameBoardController controller = new GameBoardController(view, model, boardLength);
		
		

	}

}
