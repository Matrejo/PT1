package tp1.logic;

import tp1.logic.gameobjects.Mario;
import tp1.logic.Action;
import tp1.view.Messages;

public class Game {

	public static final int DIM_X = 30;
	public static final int DIM_Y = 15;

	public int remainingTime = 0, nLevel = 0, num_lives = 3, remaining_time = 100, num_points;
	public GameObjectContainer gameObjects;
	public Mario mario;
	public boolean end_game = false, won = false;
	
	
	public Game(int nLevel) {
		// TODO Auto-generated constructor stub
	}
	
	public String positionToString(int col, int row) {
		Position coords = new Position(row, col);
		String pos_str = "";
		boolean found = false;
		
		for (int i = 0; i < gameObjects.ground_counter; i++)
			if (gameObjects.groundList[i].IsInPos(coords)) {
				pos_str = gameObjects.groundList[i].getIcon();
				found = true;
			}
		
		if (!found) {
			for(int i = 0; i < gameObjects.goomba_counter; i++)
				if (gameObjects.goombaList[i].IsInPos(coords)) {
					pos_str = gameObjects.goombaList[i].getIcon();
					found = true;
				}
		}
		
		if(!found) {
			for(int i = 0; i < gameObjects.mario_counter; i++)
				if (gameObjects.marioList[i].IsInPos(coords)) {
					pos_str = gameObjects.marioList[i].getIcon();
					found = true;
				}
		}
		
		if(!found) {
			for(int i = 0; i < gameObjects.door_counter; i++)
				if (gameObjects.doorList[i].IsInPos(coords))
					pos_str = gameObjects.doorList[i].getIcon();
		}
		
		
		
		return pos_str;
	}

	public boolean playerWins() {
		// TODO Auto-generated method stub
		return won;
	}

	public int remainingTime() {
		// TODO Auto-generated method stub
		return remaining_time;
	}

	public int points() {
		// TODO Auto-generated method stub
		return num_points;
	}

	public int numLives() {
		// TODO Auto-generated method stub
		return num_lives;
	}
	
	public void subtractLife() {
		this.num_lives--;
	}
	
	public void changeNumPoints(int addition) {
		this.num_points += addition;
	}

	@Override
	public String toString() {
		// TODO returns a textual representation of the object
		return "TODO: Hola soy el game";
	}

	public boolean playerLoses() {
		// TODO Auto-generated method stub
		return num_lives == 0;
	}
	
	public boolean hasGround(Position pos) {
		int i = 0;
		boolean found = false;
		
		while(gameObjects.groundList[i] != null && !found) {
			if (gameObjects.groundList[i].IsInPos(pos))
				found = true;
			i++;
		}
		
		return found;
	}
	
	public boolean hasGoomba(Position pos) {
		int i = 0;
		boolean found = false;
		
		while(gameObjects.goombaList[i] != null && !found) {
			if (gameObjects.goombaList[i].IsInPos(pos))
				found = true;
			i++;
		}
		
		return found;
	}
	
	public boolean eliminateGoomba(Position pos) {
		boolean found = false;
		int i = 0;
		while(gameObjects.goombaList[i] != null && !found) {
			if (gameObjects.goombaList[i].IsInPos(pos))
				found = true;
			i++;
		}
		
		if(found) {
			for(int j = i - 1; j < gameObjects.goomba_counter; j++)
				gameObjects.goombaList[j] = gameObjects.goombaList[j + 1];
			gameObjects.goomba_counter--;
		}
		
		return found;
	}
	
	
	public boolean hasDoor(Position pos) {
		int i = 0;
		boolean found = false;
		
		while(gameObjects.doorList[i] != null && !found) {
			if (gameObjects.doorList[i].IsInPos(pos))
				found = true;
			i++;
		}
		
		return found;
	}
	
	public boolean hasMario(Position pos) {
		int i = 0;
		boolean found = false;
		
		while(gameObjects.marioList[i] != null && !found) {
			if (gameObjects.marioList[i].IsInPos(pos))
				found = true;
			i++;
		}
		
		return found;
	}
	
	public void updateGoombas() {
		int i = 0;
		
		while(gameObjects.goombaList[i] != null) {
			gameObjects.goombaList[i].update();
			i++;
		}
		
	}
	
	
	public void initLevel0() {
		this.nLevel = 0;
		this.remainingTime = 100;
		
		// 1. Mapa
		gameObjects = new GameObjectContainer();
		for(int col = 0; col < 15; col++) {
			gameObjects.add(new Ground(new Position(13,col)));
			gameObjects.add(new Ground(new Position(14,col)));		
		}

		gameObjects.add(new Ground(new Position(Game.DIM_Y-3,9)));
		gameObjects.add(new Ground(new Position(Game.DIM_Y-3,12)));
		for(int col = 17; col < Game.DIM_X; col++) {
			gameObjects.add(new Ground(new Position(Game.DIM_Y-2, col)));
			gameObjects.add(new Ground(new Position(Game.DIM_Y-1, col)));		
		}

		gameObjects.add(new Ground(new Position(9,2)));
		gameObjects.add(new Ground(new Position(9,5)));
		gameObjects.add(new Ground(new Position(9,6)));
		gameObjects.add(new Ground(new Position(9,7)));
		gameObjects.add(new Ground(new Position(5,6)));
		
		// Salto final
		int tamX = 8, tamY= 8;
		int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;
		
		for(int col = 0; col < tamX; col++) {
			for (int fila = 0; fila < col+1; fila++) {
				gameObjects.add(new Ground(new Position(posIniY- fila, posIniX+ col)));
			}
		}

		gameObjects.add(new ExitDoor(new Position(Game.DIM_Y-3, Game.DIM_X-1)));

		// 3. Personajes
		this.mario = new Mario(this, new Position(Game.DIM_Y-3, 0));
		gameObjects.add(this.mario);

		gameObjects.add(new Goomba(this, new Position(0, 19)));
	}
		public void initLevel1() {
			this.nLevel = 0;
			this.remainingTime = 100;
			
			// 1. Mapa
			gameObjects = new GameObjectContainer();
			
			for(int col = 0; col < 15; col++) {
				gameObjects.add(new Ground(new Position(13,col)));
				gameObjects.add(new Ground(new Position(14,col)));		
			}

			gameObjects.add(new Ground(new Position(Game.DIM_Y-3,9)));
			gameObjects.add(new Ground(new Position(Game.DIM_Y-3,12)));
			for(int col = 17; col < Game.DIM_X; col++) {
				gameObjects.add(new Ground(new Position(Game.DIM_Y-2, col)));
				gameObjects.add(new Ground(new Position(Game.DIM_Y-1, col)));		
			}

			gameObjects.add(new Ground(new Position(9,2)));
			gameObjects.add(new Ground(new Position(9,5)));
			gameObjects.add(new Ground(new Position(9,6)));
			gameObjects.add(new Ground(new Position(9,7)));
			gameObjects.add(new Ground(new Position(5,6)));
			
			// Salto final
			int tamX = 8, tamY= 8;
			int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;
			
			for(int col = 0; col < tamX; col++) {
				for (int fila = 0; fila < col+1; fila++) {
					gameObjects.add(new Ground(new Position(posIniY- fila, posIniX+ col)));
				}
			}

			gameObjects.add(new ExitDoor(new Position(Game.DIM_Y-3, Game.DIM_X-1)));

			// 3. Personajes
			this.mario = new Mario(this, new Position(Game.DIM_Y-3, 0));
			gameObjects.add(this.mario);

			gameObjects.add(new Goomba(this, new Position(0, 19)));
			gameObjects.add(new Goomba(this, new Position(4, 6)));
			gameObjects.add(new Goomba(this, new Position(12, 6)));
			gameObjects.add(new Goomba(this, new Position(12, 8)));
			gameObjects.add(new Goomba(this, new Position(10, 10)));
			gameObjects.add(new Goomba(this, new Position(12, 11)));
			gameObjects.add(new Goomba(this, new Position(12, 14)));
	}
	
}