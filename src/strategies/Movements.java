package strategies;

import board.Board;
import boardElements.*;
import java.util.ArrayList;

public class Movements {

	public static void move(ArrayList<BoardElement> gameElements, Board board) {
		for (BoardElement e : gameElements) {
			if (e instanceof Runner runner)
				RunnerStrategy.moveRunner(gameElements, board, runner);
			else if (e instanceof Chaser chaser)
				ChaserStrategy.moveChaser(gameElements, board, chaser);
		}
	}
}
