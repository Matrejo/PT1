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
	private GameWorld game;
	private int remainingTime, points, lives;
	private String[] gameMario;
	private ArrayList<GameObject> npc_list;
	
	public FileGameConfiguration(String fileName, GameWorld game) throws GameLoadException {
		try {
			data_in = new BufferedReader( new FileReader (fileName));
			npc_list = new ArrayList<GameObject>();
			GameObject new_object = null;
			String gameInfo = data_in.readLine();
			String[] separated_info = gameInfo.split(" ");
			this.game = game;
			this.remainingTime = Integer.parseInt(separated_info[0]);
			this.points = Integer.parseInt(separated_info[1]);
			this.lives = Integer.parseInt(separated_info[2]);
			if (data_in != null) {
				data_in.close();
			}
			while((gameInfo = data_in.readLine()) != null) {
				String[] new_object_string = gameInfo.split(" ");
				if (!new_object_string[1].equalsIgnoreCase("Mario")) {
					new_object = GameObjectFactory.parse(new_object_string, game);
					npc_list.add(new_object);
				}
				else {
					gameMario = new_object_string;
				}
			}
		} catch(FileNotFoundException fnfe) {
			throw new GameLoadException(Messages.UNKNOWN_FILE_NAME_ERROR.formatted(fileName), fnfe);
		} catch(Exception e) {
			throw new GameLoadException(Messages.LOADING_GAME_ERROR, e);
		}
	}
	
	public Mario getMario() throws GameLoadException{
		Mario mario = new Mario(game, new Position(0,0));
		
		try {
			return mario.createInstance(gameMario, game);
		} catch (Exception e) {
			throw new GameLoadException(Messages.LOADING_GAME_ERROR);
		}
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
		ArrayList<GameObject> new_npc_list = new ArrayList<GameObject>();
		
		for (GameObject c : npc_list) {
			new_npc_list.add(c);
		}
		
		return new_npc_list;
	}
}
