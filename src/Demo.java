import controller.GameBoardController;
import model.GameBoard;
import view.GameBoardView;

public class Demo {

	public static void main(String[] args) {
		
		StartScreen gameStarter = new StartScreen();
		int sideLength = gameStarter.selectBoardSize();
		if(sideLength == -1) {
			System.out.println("Error selecting board size.");
		}
		else {
			GameBoard model = new GameBoard(sideLength);
			GameBoardView view = new GameBoardView(sideLength);
			GameBoardController controller = new GameBoardController(view, model, sideLength);
		}

	}

}
