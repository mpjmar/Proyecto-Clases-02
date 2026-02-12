package boardElements;

import java.util.ArrayList;

import board.Board;
import utils.ListUtils;
import utils.Position;
import utils.Utils;

public class Chaser extends Role implements Target {

	private Target target;
    
	public Chaser() {
		super(0, 0);
		this.target = null;
	}

	public Chaser(int row, int col) {
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
			if (e instanceof Runner) {
				int dist = Position.calcDistance(this.getPos(), e.getPos());
				if (dist < minDist) {
					minDist = dist;
					target = (Target) e;
				}
			}
		}
		this.target = target;
	}

	public static void generateChasers(Board board, int level, ArrayList<BoardElement> gameElements) {
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
			Chaser chaser = new Chaser(row, col);
			gameElements.add(chaser);
		}
	}
}
