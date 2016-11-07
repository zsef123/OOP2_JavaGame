package gameStates;

import java.io.*;
import java.util.HashMap;
import java.util.Vector;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import models.*;
public class Stage1 extends Stage {
	
	public Stage1(int id) {
		super(id);
		stageIndex=1;
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		bgImage=new Image("C:\\javaProject\\JavaModels\\Game_Background_Image.png");
		objs=new HashMap<Integer,GameObject>();
		cnt =0;
		
		objs.put(9, new Wall(9,"stg1_wall"));
		objs.put(1, new Player(1,"stg1_player"));
		objs.put(2, new Ball(2,"stg_ball"));
		
		a=new Image("C:\\javaProject\\JavaModels\\wall.png");
		p=new Image("C:\\javaProject\\JavaModels\\player.png");
		// map[posY][posX]
		map=new int[mapWidth][mapHeight];
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\javaProject\\JavaModels\\Stage1.txt"));
			for( int i=0; i<mapWidth;i++) {
				String line=br.readLine();
				if ( line == null) break;
				String[] lineSplit= line.split("\t");
				for( int j=0;j<mapHeight; j++) {
					map[i][j] = Integer.parseInt(lineSplit[j]) ;
					if ( map[i][j] == 1 ) {
						playerPosX=j;
						playerPosY=i;
					}
				}
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}