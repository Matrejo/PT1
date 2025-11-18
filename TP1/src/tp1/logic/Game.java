package tp1.logic;

import tp1.control.commands.Command;
import tp1.logic.gameobjects.*;
import tp1.view.*;

public class Game implements GameModel, GameStatus, GameWorld {

	public static final int DIM_X = 30;
	public static final int DIM_Y = 15;
	
	private int nLevel = 0, numLives = 3, remainingTime = 100, numPoints = 0;
	private Mario mario;
	private boolean endGame = false, won = false, exit = false, addMushroom = false, hasMario = false;
	private GameObjectContainer gameObjects;
	private Position newMushroomPos;
	
	public void doMarioActions(Action[] actions) {
        mario.setActions(actions);
        mario.moveMario();
        this.update();
    }
	
	public boolean newObject(String[] newObject) {
		boolean added = false;
		AddObject addObjectToGame = new AddObject(newObject, this);
		GameObject newGameObject = addObjectToGame.addObject(this);
		
		if (newGameObject != null) {
			if (newGameObject.getName() == "mario") {
				if (!mario.isAlive()){
					this.mario = mario.createInstance(newObject, this);
					gameObjects.add(this.mario);
					added = true;
				}
				
				else {
					System.out.println(Messages.ERROR.formatted(Messages.ERROR_EXISTING_MARIO));	
				}
			}
			
			else {
				gameObjects.add(newGameObject);
				added = true;
			}
		}
		
		return added;
	}

	//TODO fill your code
	
	public Game(int nLevel) {
		gameObjects = new GameObjectContainer();
		if (nLevel == 1) {
			this.initLevel1();
			this.nLevel = nLevel;
		}
		
		else if (nLevel == 0){
			this.initLevel0();
			this.nLevel = 0;
		}
		
		else {
			this.initLevelVoid();
			this.nLevel = -1;
		}
	}

	public String positionToString(int col, int row) {

		Position pos = new Position(row, col);
		
		return this.gameObjects.toString(pos);
	}

	public boolean playerWins() {
		return won;
	}
	
	public void restart() {
		gameObjects = new GameObjectContainer();
		this.numLives = 3;
		this.remainingTime = 100;
		this.numPoints = 0;
		if (nLevel == 1) {
			this.initLevel1();
			this.nLevel = 1;
		}
		
		else if (nLevel == 0){
			this.initLevel0();
			this.nLevel = 0;
		}
		
		else {
			this.initLevelVoid();
			this.nLevel = -1;
		}
	}
	
	public void setLevel(int level) {
		this.nLevel = level;
	}

	public int remainingTime() {
		// TODO Auto-generated method stub
		return remainingTime;
	}

	public int points() {
		// TODO Auto-generated method stub
		return numPoints;
	}

	public int numLives() {
		// TODO Auto-generated method stub
		return numLives;
	}

	@Override
	public String toString() {
		// TODO returns a textual representation of the object
		return "TODO: Hola soy el game";
	}
	
	public void reachedDoor() {
		this.won = true;
	}

	public boolean isFinished() {
		this.endGame = (won || numLives == 0 || exit || this.playerLoses());
		return endGame;
	}

	public boolean playerLoses() {
		boolean lost = false;
		if(numLives == 0 || remainingTime == 0) {
			lost = true;
		}
		return lost;
	}
	
	public void addPoints(int new_points) {
		this.numPoints += new_points;
	}
	
	public void addMushroom(Position pos) {
		this.addMushroom = true;
		this.newMushroomPos = pos;
	}
	
	// Not mandatory but recommended
	public void exit() {
		// TODO Auto-generated method stub
		this.exit = true;
	}
	
	public boolean isSolid(Position pos) {
		return gameObjects.hasSolid(pos);
	}
	
	public void doInteractionsFrom(GameItem obj) {
		gameObjects.doInteractions(obj);
	}
	
	public void update() {
		if (addMushroom) {
			gameObjects.add(new Mushroom(this, newMushroomPos.add_y(newMushroomPos, -1)));
			addMushroom = false;
		}
		gameObjects.update();
		this.doInteractionsFrom(mario);
		this.remainingTime--;
		if(!mario.isAlive()) {
			mario.alive();
			this.numLives--;
			gameObjects = new GameObjectContainer();
			if (nLevel == 1) {
				this.initLevel1();
				this.nLevel = 1;
			}
			
			else if (nLevel == 0){
				this.initLevel0();
				this.nLevel = 0;
			}
			
			else {
				this.initLevelVoid();
				this.nLevel = -1;
			}
		}
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
	
	public void initLevelVoid() {
		this.nLevel = 0;
		this.remainingTime = 100;
		
		// 1. Mapa
		gameObjects = new GameObjectContainer();
		
		// Salto final
		int tamX = 8, tamY= 8;
		int posIniX = Game.DIM_X-3-tamX, posIniY = Game.DIM_Y-3;
		
		this.mario = new Mario(this, new Position(Game.DIM_Y-3, 0));
		this.mario.dead();
	}
}
