package tp1.logic;

import tp1.exceptions.*;
import tp1.logic.gameobjects.Mario;
import tp1.view.Messages;
import tp1.logic.gameobjects.*;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import tp1.logic.Position;


public class FileGameConfiguration implements GameConfiguration{
	private BufferedReader data_in = null;
	private String fileName = null;
	private GameWorld game;
	private int remainingTime, points, lives;
	
	public FileGameConfiguration(String fileName, GameWorld game) throws GameLoadException {
		try {
			data_in = new BufferedReader( new FileReader (fileName));
			this.fileName = fileName;
			String gameInfo = data_in.readLine();
			String[] separated_info = gameInfo.split(" ");
			this.game = game;
			this.remainingTime = Integer.parseInt(separated_info[0]);
			this.points = Integer.parseInt(separated_info[1]);
			this.lives = Integer.parseInt(separated_info[2]);
		} catch(FileNotFoundException fnfe) {
			throw new GameLoadException(Messages.UNKNOWN_FILE_NAME_ERROR, fnfe);
		} catch(Exception e) {
			throw new GameLoadException(Messages.LOADING_GAME_ERROR, e);
		}
	}
	
	public Mario getMario() throws GameLoadException{
		Mario mario = new Mario(game, new Position(0,0));
		
		String input_string;

		try {
			data_in = new BufferedReader( new FileReader (fileName));
			while((input_string = data_in.readLine()) != null) {
				String[] new_object_string = input_string.split(" ");
				if (new_object_string[1].equalsIgnoreCase("Mario")) {
					mario = mario.createInstance(new_object_string, game);
				}
			}
		} catch(Exception e) {
			throw new GameLoadException(Messages.LOADING_GAME_ERROR, e);
		}
		
		return mario;
	}
	
	public int getRemainingTime() {		
		return remainingTime;
	}
	
	public int points() {
		return points;
	}
	
	public int numLives() {
		return lives;
	}
	
	public List<GameObject> getNPCObjects() throws GameLoadException {
		ArrayList<GameObject> npc_list = new ArrayList<GameObject>();
		String input_string;
		GameObject new_object = null;
		
		try {
			data_in = new BufferedReader( new FileReader (fileName));
			input_string = data_in.readLine();
			while((input_string = data_in.readLine()) != null) {
				String[] new_object_string = input_string.split(" ");
				if (!new_object_string[1].equalsIgnoreCase("Mario")) {
					new_object = GameObjectFactory.parse(new_object_string, game);
					npc_list.add(new_object);
				}
			}
		} catch(Exception e) {
			throw new GameLoadException(Messages.LOADING_GAME_ERROR, e);
		}
		return npc_list;
	}
}
