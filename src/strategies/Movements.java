package strategies;

import java.util.ArrayList;
import board.Board;
import boardElements.*;

public class Movements {

	public static void move(ArrayList<BoardElement> gameElements, Board board) {
		for (BoardElement e : gameElements) {
			if (e instanceof Runner)
				RunnerStrategy.moveRunner(gameElements, board, (Runner) e);
			else if (e instanceof Chaser)
				ChaserStrategy.moveChaser(gameElements, board, (Chaser) e);
		}
	}
}
