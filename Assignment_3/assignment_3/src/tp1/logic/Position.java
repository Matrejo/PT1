package tp1.logic;


import tp1.exceptions.OffBoardException;
import tp1.exceptions.PositionParseException;
import tp1.view.Messages;
/**
 * 
 * TODO: Immutable class to encapsulate and manipulate positions in the game board
 * 
 */
public class Position {

	private int col;
	private int row;

	public Position(int y, int x) {
		this.row = y;
		this.col = x;
	}
	
	public boolean equals(Position pos) {
		return (this.row == pos.row && this.col == pos.col);
	}
	
	public Position add_y(Position pos, int pos_change) {
		Position new_pos = new Position(pos.row + pos_change, pos.col);
		
		return new_pos;
	}
	
	public Position add_x(Position pos, int pos_change) {
		Position new_pos = new Position(pos.row, pos.col + pos_change);
		
		return new_pos;
	}
	
	public boolean outOfBounds() {
		return (this.row < 0 || this.row > 14 || this.col < 0 || this.col > 29);
	}
	
	public void newObjectPos() throws OffBoardException{
		
	}
	
	public Position coordsToPos(String newPos) throws OffBoardException, PositionParseException {
		int x = 0, y = 0;
		String aux;
		
		if(newPos.charAt(2) == ',') {
			aux = newPos.substring(1, 2);
			y = Integer.parseInt(aux);
			
			if (newPos.charAt(4) == ')') {
				aux = newPos.substring(3, 4);
				x = Integer.parseInt(aux);
			}
			else {
				aux = newPos.substring(3, 5);
				x = Integer.parseInt(aux);
			}
		}
		else if (newPos.charAt(3) == ','){
			aux = newPos.substring(1, 3);
			y = Integer.parseInt(aux);
			
			if (newPos.charAt(5) == ')') {
				aux = newPos.substring(4, 5);
				x = Integer.parseInt(aux);
			}
			else {
				aux = newPos.substring(4, 6);
				x = Integer.parseInt(aux);
			}
		}
		else {
			throw new PositionParseException(Messages.UNRECOGNISABLE_POSITION_ERROR.formatted(newPos));
		}
		
		if (new Position(x, y).outOfBounds()) {
			throw new OffBoardException(Messages.OBJECT_OUT_OF_BOUNDS_ERROR.formatted(newPos));
		}
		
		return new Position(x, y);
	}
	
	public String toString() {
		StringBuilder posString = new StringBuilder();
		
		posString.append("(").append(col).append(",").append(row).append(")");
		
		return posString.toString();
	}
}