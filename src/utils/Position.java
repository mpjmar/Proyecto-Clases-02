package utils;

public class Position {
	
	private int row;
	private int col;
	// private Position pos;

	public Position() {
		this.row = 0;
		this.col = 0;
		// this.pos = new Position(0, 0);
	}

	public Position(int row, int col) {
		this.row = row;
		this.col = col;
		// this.pos = new Position(row, col);
	}

    /* public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    } */

	public int getRow() {
		return this.row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return this.col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	@Override
	public String toString() {
		return "Position: row " + this.row + "| col " + this.col + "\n";
	}

	// Manhattan distance
	public static int calcDistance(Position p1, Position p2) {
		return Math.abs(p1.getRow() - p2.getRow()) + Math.abs(p1.getCol() - p2.getCol());
	}

}
