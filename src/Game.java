import board.Board;
import boardElements.*;
import input.*;
import java.util.ArrayList;
import strategies.Fight;
import strategies.Movements;
import utils.*;


public class Game {

	public static final String GREEN = "\033[0;32m";
	public static final String RED = "\033[0;31m";
	public static final String BLUE = "\033[0;34m";
	public static final String RESET = "\033[0m";

	public static void main(String[] args) throws Exception {

		int option = -1;
		int rows = -1;
		int cols = -1;
		int level = 1;

		do {
			option = Utils.displayMenu();
			switch (option) {
				case 1:
					System.out.println("Please, enter the board size: ");
					System.out.print("Heigth (10 - 100): ");
					rows = Input.readInteger(10, 50);
					System.out.print("Width (10 - 100): ");
					cols = Input.readInteger(10, 50);
					launch(rows, cols, level);
					break;
				case 2:
					level = Utils.displaySubmenu();
					break;
				case 3:
					Utils.gameMechanics();
					break;
				default:
					break;
			}
		} while (option != 4);
	}

	public static void launch(int rows, int cols, int level) throws InterruptedException {
		Board board = new Board(rows, cols);
		ArrayList<BoardElement> gameElements = new ArrayList<BoardElement>();
		int runners = 0;
		int chasers = 0;
		int moves = 0;
		boolean erased;
		Obstacle.generateObstacles(board, level, gameElements);
		Chaser.generateChasers(board, level, gameElements);
		Runner.generateRunners(board, level, gameElements);
		
		do {
			erased = false;
			Utils.clearConsole();
			board.clearBoard();
			
			for (BoardElement e : gameElements)
				if (e instanceof Target character)
					character.setTarget(gameElements);
			board.placeElements(gameElements);

			System.out.println("Esto se imprime");

			Movements.move(gameElements, board);
			Fight.searchEnemies(gameElements);

			System.out.println(board);
			System.out.printf("Runners: %s%d%s  |  Chasers: %s%d%s%n",
				GREEN, ListUtils.countCharacters(gameElements, "Runner"), RESET, 
				RED, ListUtils.countCharacters(gameElements, "Chaser"), RESET);
			System.out.println(ListUtils.displayState(gameElements));

			erased = ListUtils.updateList(gameElements);
			runners = ListUtils.countCharacters(gameElements, "Runner");
			chasers = ListUtils.countCharacters(gameElements, "Chaser");
			
			if (erased)
				moves = 0;
			else
				moves++;
			Thread.sleep(500);
		} while ((runners > 0 && chasers > 0) && moves < 50);
		
		Utils.displayWinner(gameElements);
	}
}
