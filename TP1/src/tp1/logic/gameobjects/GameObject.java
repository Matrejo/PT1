package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.GameWorld;
import tp1.logic.GameModel;
import tp1.logic.GameStatus;
import tp1.logic.Position;

public abstract class GameObject implements GameItem { // TODO 

	protected Position pos; // If you can, make it private.
	private boolean isAlive;
	protected GameWorld game;
	protected boolean solid;
	
	public GameObject(GameWorld game, Position pos, boolean solid) {
		this.isAlive = true;
		this.pos = pos;
		this.game = game;
		this.solid = solid;
	}
	
	public GameObject() {
		this.pos = new Position(0, 0);
	}
	
	public boolean isInPosition(Position p) {
		return this.pos.equals(p);
	}
	
 	
	public abstract GameObject createInstance(String[] info, GameWorld game);
	
	public GameObject parse(String objWords[], GameWorld game) {
		GameObject object = null;
		if (objWords.length > 1 && (5 <= objWords[0].length() && objWords[0].length() < 8)) {
			if (this.matchObjectName(objWords[1])) {
				object = this.createInstance(objWords, game);
			}
		}
		
		return object;
	}
	
	protected boolean matchObjectName(String name) {
		return getShortcut().equalsIgnoreCase(name) || getName().equalsIgnoreCase(name);
	}
	
	public abstract String getShortcut();
	
	public abstract String getName();
	
	public boolean isAlive() {
		return isAlive;
	}
	
	public void dead(){
		this.isAlive = false;
	}
	
	public void alive() {
		this.isAlive = true;
	}
	
	public boolean isSolid() {
		return solid;
	}
	
	public abstract void update();
	
	public abstract String getIcon();

	// Not mandatory but recommended
	protected void move(Action dir) {
		switch (dir) {
		case UP:
			this.pos = this.pos.add_y(pos, -1);
			break;
		
		case DOWN:
			this.pos = this.pos.add_y(pos, 1);
			break;
			
		case RIGHT:
			this.pos = this.pos.add_x(pos, 1);
			break;
			
		case LEFT:
			this.pos = this.pos.add_x(pos, -1);
			break;
			
		case STOP:
			break;
		}
	}
	
	public boolean receiveInteraction(Mario other) {
		return false;
	}
	
	public boolean receiveInteraction(Ground ground) {
		return false;
	}
	
	public boolean receiveInteraction(ExitDoor door) {
		return false;
	}
	
	public boolean receiveInteraction(Goomba goomba) {
		return false;
	}
	
	public boolean receiveInteraction(Mushroom mushroom) {
		return false;
	}
	
	public boolean receiveInteraction(Box box) {
		return false;
	}
}
