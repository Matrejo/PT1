package tp1.logic;

import tp1.exceptions.GameLoadException;
import tp1.logic.gameobjects.Mario;
import tp1.logic.gameobjects.*;
import java.util.List;
import java.util.ArrayList;
import java.io.*;


public class FileGameConfiguration implements GameConfiguration{
	private BufferedReader data_in = null;
	private GameWorld game;
	
	public FileGameConfiguration(String fileName, GameWorld game) throws GameLoadException {
		try {
			data_in = new BufferedReader( new FileReader (fileName));
			
		} catch(FileNotFoundException fnfe) {
			
		}
	}
	
	public Mario getMario(){
		Mario mario = new Mario();
		
		return mario;
	}
	
	public int getRemainingTime() {
		
		
		return 0;
	}
	
	public int points() {
		
		
		return 0;
	}
	
	public int numLives() {
		
		
		return 0;
	}
	
	public List<GameObject> getNPCObjects() {
		ArrayList<GameObject> npc_list = new ArrayList<GameObject>();
		String new_object;
		
		try {
			new_object = data_in.readLine();
			
		} catch(IOException ioe) {
			
		}
		return npc_list;
	}
}
