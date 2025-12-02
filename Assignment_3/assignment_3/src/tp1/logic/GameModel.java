package tp1.logic;
import tp1.exceptions.*;
import java.io.*;

public interface GameModel {
	public boolean isFinished();
	public void update();
	public void restart();
	public void doMarioActions(Action[] actions);
	public void exit();
	public void setLevel(int level);
	public void newObject(String[] newObject) throws GameModelException;
	public void save(String fileName) throws CommandExecuteException, FileNotFoundException, IOException;
}
