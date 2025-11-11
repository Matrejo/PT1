package tp1.logic;

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
	
	public Position coordsToPos(String newPos) {
		int x = 0, y = 0;
		String aux;
		
		if(newPos.charAt(2) == ',') {
			aux = newPos.substring(1, 2);
			x = Integer.parseInt(aux);
			
			if (newPos.charAt(4) == ')') {
				aux = newPos.substring(3, 4);
				y = Integer.parseInt(aux);
			}
			else {
				aux = newPos.substring(3, 5);
				y = Integer.parseInt(aux);
			}
		}
		else {
			aux = newPos.substring(1, 3);
			x = Integer.parseInt(aux);
			
			if (newPos.charAt(5) == ')') {
				aux = newPos.substring(4, 5);
				y = Integer.parseInt(aux);
			}
			else {
				aux = newPos.substring(4, 6);
				y = Integer.parseInt(aux);
			}
		}
		
		return new Position(x, y);
	}
}