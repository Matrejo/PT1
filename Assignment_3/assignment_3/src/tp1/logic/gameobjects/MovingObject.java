package tp1.logic.gameobjects;

import tp1.logic.Action;
import tp1.logic.GameWorld;
import tp1.logic.Position;

public class MovingObject extends GameObject {
	private boolean right = true, falling = false;
	
	public MovingObject(GameWorld game, Position pos, boolean solid) {
		super(game, pos, solid);
	}
	
	public void setRight(boolean right) {
		this.right = right;
	}
	
	public void setFalling(boolean right) {
		this.falling = right;
	}
	
	public boolean getRight() {
		return right;
	}
	
	public boolean getFalling() {
		return falling;
	}
	
	public MovingObject() {
		this.pos = new Position(0, 0);
	}
	
	public boolean isInPosition(Position p) {
		return this.pos.equals(p);
	}
	
	public GameObject parse(String objWords[], GameWorld game) {
		GameObject object = null;
		
		object = super.parse(objWords, game);
		
		return object;
	}
	
	protected boolean matchObjectName(String name) {
		return getShortcut().equalsIgnoreCase(name) || getName().equalsIgnoreCase(name);
	}
	
	public GameObject createInstance(String[] newObject, GameWorld game) {
		return this.createInstance(newObject, game);
	}
	
	public void update() {
		
	}
	
	public String getName() {
		return this.getName();
	}
	
	public String getShortcut() {
		return this.getShortcut();
	}
	
	public String getIcon() {
		return this.getIcon();
	}

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
	
	public boolean interactWith(GameItem object) {
		return false;
	}
}
