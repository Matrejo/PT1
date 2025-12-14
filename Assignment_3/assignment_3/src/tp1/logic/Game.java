package tp1.logic;

import tp1.control.commands.Command;
import tp1.exceptions.*;
import tp1.logic.gameobjects.*;
import tp1.view.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Game implements GameModel, GameStatus, GameWorld {

	public static final int DIM_X = 30;
	public static final int DIM_Y = 15;
	
	private int nLevel = 0, numLives = 3, remainingTime = 100, numPoints = 0;
	private Mario mario;
	private boolean endGame = false, won = false, exit = false, addMushroom = false, hasMario = false;
	private GameObjectContainer gameObjects;
	private Position newMushroomPos;
	private GameConfiguration previousConfig = null;
	
	public void doMarioActions(Action[] actions) {
        mario.setActions(actions);
        mario.moveMario();
        this.update();
    }
	
	public void newObject(String[] newObject) throws GameModelException {

		AddObject addObjectToGame = new AddObject(newObject, this);
		GameObject newGameObject = addObjectToGame.addObject(this);
		
		try {
			if (newGameObject != null) {
				GameObject obj = null;
				obj = mario.parse(newObject, this);
				if (obj != null) {
					if (!mario.isAlive()){
						this.mario = mario.createInstance(newObject, this);
						gameObjects.add(this.mario);
					}
					
					else {
						this.mario.dead();
						this.mario = mario.createInstance(newObject, this);
						gameObjects.add(this.mario);
					}
				}
				
				else {
					gameObjects.add(newGameObject);
				}
			}
		} catch (PositionParseException ppe) {
			throw new GameModelException(Messages.OBJECT_ERROR, ppe);
		}
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
	
	public void restart() throws GameLoadException {
		if (previousConfig == null) {
			gameObjects = new GameObjectContainer();
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
				this.numLives = 3;
			}
		}
		
		else {
			List<GameObject> npc_list = previousConfig.getNPCObjects();
			this.gameObjects = new GameObjectContainer();
			
			for (GameObject c : npc_list) {
				this.gameObjects.add(c);
			}
			
			this.mario = previousConfig.getMario();
			this.gameObjects.add(this.mario);
			this.remainingTime = previousConfig.getRemainingTime();
			this.numPoints = previousConfig.points();
			this.numLives = previousConfig.numLives();
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
		StringBuilder gameToString = new StringBuilder();
		gameToString.append(remainingTime).append(" ").append(numPoints).append(" ").append(numLives).append(Messages.LINE_SEPARATOR);
		gameToString.append(gameObjects.toString());
		gameToString.append(gameObjects.toString());
		return gameToString.toString();
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
			int currentTime = remainingTime;
			mario.alive();
			this.numLives--;
			gameObjects = new GameObjectContainer();
			if (nLevel == 1) {
				this.initLevel1();
				this.nLevel = 1;
				this.remainingTime = currentTime;
			}
			
			else if (nLevel == 0){
				this.initLevel0();
				this.nLevel = 0;
				this.remainingTime = currentTime;
			}
			
			else {
				this.initLevelVoid();
				this.nLevel = -1;
				this.remainingTime = currentTime;
			}
		}
	}
	
	public void save(String fileName) throws GameSaveException{
		BufferedWriter outFile = null;
		try {
			outFile = new BufferedWriter( new FileWriter (fileName));
			String finalString = this.toString();
			outFile.write(finalString.toString());
			if (outFile != null) {
				outFile.close();
			}
		} catch(FileNotFoundException fnfe) {
			throw new GameSaveException(Messages.UNKNOWN_FILE_NAME_ERROR.formatted(fileName), fnfe);
		} catch (IOException ioe) {
			throw new GameSaveException(Messages.SAVING_GAME_ERROR, ioe);
		}
	}
	
	public void load(String fileName) throws GameLoadException {
		FileGameConfiguration new_file_data = new FileGameConfiguration(fileName, this);
		this.previousConfig = new_file_data;
		List<GameObject> npc_list = new_file_data.getNPCObjects();
		this.gameObjects = new GameObjectContainer();
		
		for (GameObject c : npc_list) {
			this.gameObjects.add(c);
		}
		
		this.mario = new_file_data.getMario();
		this.gameObjects.add(this.mario);
		this.remainingTime = new_file_data.getRemainingTime();
		this.numPoints = new_file_data.points();
		this.numLives = new_file_data.numLives();
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
		this.nLevel = 1;
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
		this.nLevel = -1;
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
