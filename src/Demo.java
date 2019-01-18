import controller.GameBoardController;
import controller.SelectSimulator;
import controller.StartScreen;
import model.GameBoard;
import robot.SimulatorController;
import view.GameBoardView;

public class Demo {

	public static void main(String[] args) {
		
		SelectSimulator selectSimulator = new SelectSimulator();
		boolean simulation = selectSimulator.selectSimulateMode();

		StartScreen gameStarter = new StartScreen();
		int sideLength = gameStarter.selectBoardSize();
		if(sideLength == -1) {
			System.out.println("Error selecting board size.");
		}
		else {
			GameBoard model = new GameBoard(sideLength);
			GameBoardView view = new GameBoardView(sideLength);
			@SuppressWarnings("unused")
			GameBoardController controller = new GameBoardController(view, model, sideLength);
			
			if(simulation) {
				SimulatorController robot = new SimulatorController (view);
				robot.controlGame();
			}
		}


	}

}
