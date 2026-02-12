package utils;

import java.util.ArrayList;
import boardElements.BoardElement;
import boardElements.Chaser;
import boardElements.Role;
import boardElements.Runner;

public class ListUtils {
	
	public static boolean isEmpty(ArrayList<BoardElement> gameElements, int row, int col) {
		for (BoardElement e : gameElements) {
			if (e.getRow() == row && e.getCol() == col)
				return false;
		}	
		return true;
	}

	public static int countCharacters(ArrayList<BoardElement> gameElements, String character) {
		int counter = 0;
		for (BoardElement e : gameElements)
			if (e.getClass().getSimpleName().equals(character))
				counter++;
		return counter;
	}

	public static boolean updateList(ArrayList<BoardElement> gameElements) {
		return gameElements.removeIf(e -> e instanceof Role && ((Role) e).getLife() == 0);
	}

	public static String displayState(ArrayList<BoardElement> gameElements) {
		String state = "";
		for (BoardElement e : gameElements)
			if ((e instanceof Chaser || e instanceof Runner) && ((Role) e).getLife() == 0)
				state += "A " + e.getClass().getSimpleName() + " has died.\n";
		return state;
	}
}
