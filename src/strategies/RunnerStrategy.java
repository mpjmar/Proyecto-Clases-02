package strategies;

import board.Board;
import boardElements.BoardElement;
import boardElements.Runner;
import java.util.ArrayList;
import utils.MovUtils;
import utils.Position;


public class RunnerStrategy {

	public static void moveRunner(ArrayList<BoardElement> gameElements, Board board, Runner r) {
		Position bestPos = calcBestPos(gameElements, board, r);
		r.setRow(bestPos.getRow());
		r.setCol(bestPos.getCol());
		r.setPos(r.getRow(), r.getCol());
	}

	private static Position calcBestPos(ArrayList<BoardElement> gameElements, Board board, Runner r) {
		Position bestPos = null;
		Position rowMov = null;
		Position colMov = null;
		int distRow = 0;
		int distCol = 0;

		/* if (r.getTarget() != null) {
			distRow = r.getTarget().getRow() - r.getRow();
			distCol = r.getTarget().getCol() - r.getCol();
		}

		rowMov = moveRow(r, distRow);
		colMov = moveCol(r, distCol);

		if (Math.abs(distRow) > Math.abs(distCol)) {
			if (isValid(gameElements, board, rowMov))
				bestPos = rowMov;
			else if (isValid(gameElements, board, colMov))
				bestPos = colMov;
		}
		else if (Math.abs(distCol) > Math.abs(distRow)) {
			if (isValid(gameElements, board, colMov))
				bestPos = colMov;
			else if (isValid(gameElements, board, rowMov))
				bestPos = rowMov;
		}
		if (bestPos == null) {
			do {
				bestPos = MovUtils.randomPos(r.getPos());
			} while (bestPos == null || !isValid(gameElements, board, bestPos));
		}
		return bestPos;
	} */

	if (r.getTarget() != null) {
		distRow = r.getTarget().getRow() - r.getRow();
		distCol = r.getTarget().getCol() - r.getCol();
	}

	rowMov = moveRow(r, distRow);
	colMov = moveCol(r, distCol);

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
			bestPos = MovUtils.randomPos(r.getPos());
		} while (bestPos == null || !MovUtils.isValid(gameElements, board, bestPos));
	}
	return bestPos;
}

	private static Position moveRow(Runner r, int distRow) {
		int mov = distRow > 0 ? -1 : 1;
		return new Position(r.getRow() + mov, r.getCol());
	}

	private static Position moveCol(Runner r, int distCol) {
		int mov = distCol > 0 ? -1 : 1;
		return new Position(r.getRow(), r.getCol() + mov);
	}

}
