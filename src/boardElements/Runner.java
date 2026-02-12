package boardElements;

import java.util.ArrayList;

import board.Board;
import utils.ListUtils;
import utils.Position;
import utils.Utils;

public class Runner extends Role implements Target {

	private Target target;

	public Runner() {
		super(0, 0);
		this.target = null;
	}

	public Runner(int row, int col) {
		super(row, col);
		this.target = null;
	}

	public Target getTarget() {
		return this.target;
	}

	@Override
	public void setTarget(ArrayList<BoardElement> gameElements) {
		int minDist = Integer.MAX_VALUE;
		Target target = null;
		for (BoardElement e : gameElements) {
			if (e instanceof Chaser) {
				int dist = Position.calcDistance(this.getPos(), e.getPos());
				if (dist < minDist) {
					minDist = dist;
					target = (Target) e;
				}
			}
		}
		this.target = target;
	}

	public static void generateRunners(Board board, int level, ArrayList<BoardElement> gameElements) {
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
			Runner runner = new Runner(row, col);
			gameElements.add(runner);
		}
	}
}
