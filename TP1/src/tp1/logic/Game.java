package tp1.logic;

import tp1.logic.gameobjects.Mario;
import tp1.logic.gameobjects.Ground;
import tp1.logic.gameobjects.ExitDoor;
import tp1.logic.gameobjects.Goomba;
import tp1.logic.gameobjects.GameItem;

public class Game {

	public static final int DIM_X = 30;
	public static final int DIM_Y = 15;
	
	private int nLevel = 0, numLives = 3, remainingTime = 100, numPoints = 0;
	private Mario mario;
	private boolean endGame = false, won = false;
	
	private GameObjectContainer gameObjects;
	
	public void doMarioActions(Action[] actions) {
        mario.setActions(actions);
        mario.moveMario();
        this.update();
    }


	//TODO fill your code
	
	public Game(int nLevel) {
		gameObjects = new GameObjectContainer();
		if (nLevel == 1) {
			this.initLevel1();
			this.nLevel = nLevel;
		}
		
		else {
			this.initLevel0();
			this.nLevel = 0;
		}
	}

	public String positionToString(int col, int row) {

		Position pos = new Position(row, col);
		
		return this.gameObjects.toString(pos);
	}

	public boolean playerWins() {
		// TODO Auto-generated method stub
		return false;
	}

	public int remainingTime() {
		// TODO Auto-generated method stub
		return remainingTime;
	}

	public int points() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int numLives() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public String toString() {
		// TODO returns a textual representation of the object
		return "TODO: Hola soy el game";
	}

	public boolean isFinished() {
		// TODO Auto-generated method stub
		return endGame;
	}

	public boolean playerLoses() {
		// TODO Auto-generated method stub
		return false;
	}
	
	// Not mandatory but recommended
	public void exit() {
		// TODO Auto-generated method stub
		endGame = true;
	}
	
	public boolean hasGround(Position pos) {
		return gameObjects.hasGround(pos);
	}
	
	public void doInteractionsFrom(GameItem obj) {
		gameObjects.doInteractions(obj);
	}
	
	public void update() {
		gameObjects.update();
	}
	

	private void initLevel0() {
		this.nLevel = 0;
		this.remainingTime = 100;
		
		// 1. Mapa
		gameObjects = new GameObjectContainer();
		for(int col = 0; col < 15; col++) {
			gameObjects.add(new Ground(this, new Position(13,col)));
			gameObjects.add(new Ground(this, new Position(14,col)));		
		}

		gameObjects.add(new Ground(this, new Position(Game.DIM_Y-3,9)));
		gameObjects.add(new Ground(this, new Position(Game.DIM_Y-3,12)));
		for(int col = 17; col < Game.DIM_X; col++) {
			gameObjects.add(new Ground(this, new Position(Game.DIM_Y-2, col)));
			gameObjects.add(new Ground(this, new Position(Game.DIM_Y-1, col)));		
		}

		gameObjects.add(new Ground(this, new Position(9,2)));
		gameObjects.add(new Ground(this, new Position(9,5)));
		gameObjects.add(new Ground(this, new Position(9,6)));
		gameObjects.add(new Ground(this, new Position(9,7)));
		gameObjects.add(new Ground(this, new Position(5,6)));
		
		// Salto final
		int tamX = 8, tamY= 8;
		int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;
		
		for(int col = 0; col < tamX; col++) {
			for (int fila = 0; fila < col+1; fila++) {
				gameObjects.add(new Ground(this, new Position(posIniY- fila, posIniX+ col)));
			}
		}

		gameObjects.add(new ExitDoor(this, new Position(Game.DIM_Y-3, Game.DIM_X-1)));

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
				gameObjects.add(new Ground(this, new Position(13,col)));
				gameObjects.add(new Ground(this, new Position(14,col)));		
			}

			gameObjects.add(new Ground(this, new Position(Game.DIM_Y-3,9)));
			gameObjects.add(new Ground(this, new Position(Game.DIM_Y-3,12)));
			for(int col = 17; col < Game.DIM_X; col++) {
				gameObjects.add(new Ground(this, new Position(Game.DIM_Y-2, col)));
				gameObjects.add(new Ground(this, new Position(Game.DIM_Y-1, col)));		
			}

			gameObjects.add(new Ground(this, new Position(9,2)));
			gameObjects.add(new Ground(this, new Position(9,5)));
			gameObjects.add(new Ground(this, new Position(9,6)));
			gameObjects.add(new Ground(this, new Position(9,7)));
			gameObjects.add(new Ground(this, new Position(5,6)));
			
			// Salto final
			int tamX = 8, tamY= 8;
			int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;
			
			for(int col = 0; col < tamX; col++) {
				for (int fila = 0; fila < col+1; fila++) {
					gameObjects.add(new Ground(this, new Position(posIniY- fila, posIniX+ col)));
				}
			}

			gameObjects.add(new ExitDoor(this, new Position(Game.DIM_Y-3, Game.DIM_X-1)));

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
