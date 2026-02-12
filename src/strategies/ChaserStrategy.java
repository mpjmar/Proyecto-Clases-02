package strategies;

import board.Board;
import boardElements.BoardElement;
import boardElements.Chaser;
import java.util.ArrayList;
import utils.MovUtils;
import utils.Position;

public class ChaserStrategy {
 
	public static void moveChaser(ArrayList<BoardElement> gameElements, Board board, Chaser c) {
		Position bestPos = calcBestPos(gameElements, board, c);
		c.setRow(bestPos.getRow());
		c.setCol(bestPos.getCol());
		c.setPos(c.getRow(), c.getCol());
	}
    

	private static Position calcBestPos(ArrayList<BoardElement> gameElements, Board board, Chaser c) {
		Position bestPos = null;
		Position rowMov = null;
		Position colMov = null;
		int distRow = 0;
		int distCol = 0;

		if (c.getTarget() != null) {
			distRow = c.getTarget().getRow() - c.getRow();
			distCol = c.getTarget().getCol() - c.getCol();
		}

		rowMov = moveRow(c, distRow);
		colMov = moveCol(c, distCol);

		if (Math.abs(distRow) > Math.abs(distCol)) {
			if (MovUtils.isValid(gameElements, board, rowMov))
				bestPos = rowMov;
			else if (MovUtils.isValid(gameElements, board, colMov))
				bestPos = colMov;
		}
		else if (Math.abs(distCol) > Math.abs(distRow)) {
			if (MovUtils.isValid(gameElements, board, colMov))
				bestPos = colMov;
			else if (MovUtils.isValid(gameElements, board, rowMov))
				bestPos = rowMov;
		}
		if (bestPos == null) {
			do {
				bestPos = MovUtils.randomPos(c.getPos());
			} while (bestPos == null || !MovUtils.isValid(gameElements, board, bestPos));
		}
		return bestPos;
	}

	private static Position moveRow(Chaser c, int distRow) {
		int mov = distRow > 0 ? 1 : -1;
		return new Position(c.getRow() + mov, c.getCol());
	}

	private static Position moveCol(Chaser c, int distCol) {
		int mov = distCol > 0 ? 1 : -1;
		return new Position(c.getRow(), c.getCol() + mov);
	}

}
