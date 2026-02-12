package boardElements;

import java.util.ArrayList;

import board.Board;
import utils.ListUtils;
import utils.Utils;

public class Obstacle extends BoardElement {
	
	public Obstacle() {
		super(0, 0);
	}

	public Obstacle(int row, int col) {
		super(row, col);
	}

	public static void generateObstacles(Board board, int level, ArrayList<BoardElement> gameElements) {
		int row;
		int col;
		int totalCells = board.getRows() * board.getCols();

		int maxElements = level == 1 ? totalCells / 20 : level == 2 ? totalCells / 15 : totalCells / 10;
		int elements = Utils.generateRandom(maxElements / 2, maxElements);
		
		for (int i = 0; i < elements; i++) {
			do {
				row = Utils.generateRandom(0, board.getRows());
				col = Utils.generateRandom(0, board.getCols());
			} while (!ListUtils.isEmpty(gameElements, row, col));
			Obstacle obstacle = new Obstacle(row, col);
			gameElements.add(obstacle);
		}
	}
}
