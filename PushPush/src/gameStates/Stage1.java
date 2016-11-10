package gameStates;

import java.io.*;
import java.util.HashMap;
import java.util.Vector;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import controller.GameObjectID;
import controller.GameStateID;
import models.*;
public class Stage1 extends Stage {
	protected int maxCnt;
	public Stage1(int id) {
		super(id);
		stageIndex=1;
	}
	
	@Override
	// init은 main에서 한번만 불린다 state의 전환과 무관하다.
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		this.game=sbg;
		// TODO Auto-generated method stub
		bgImage=new Image("C:\\javaProject\\JavaModels\\Game_Background_Image.png");
		objs=new HashMap<Integer,GameObject>();
		
		objs.put(9, new Wall(9,"stg1_wall"));
		objs.put(1, new Player(1,"stg1_player"));
		objs.put(2, new Ball(2,"stg_ball"));
		objs.put(3, new Target(3,"stg1_target"));
		objs.put(5, new Target(5,"stg1_filledtarget"));
		// map[posY][posX]
		map=new int[mapWidth][mapHeight];
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:\\javaProject\\JavaModels\\Stages\\Stage1.txt"));
			for( int i=0; i<mapWidth;i++) {
				String line=br.readLine();
				if ( line == null) break;
				String[] lineSplit= line.split("\t");
				for( int j=0;j<mapHeight; j++) {
					map[i][j] = Integer.parseInt(lineSplit[j]) ;
					if ( map[i][j] == 1 ) {
						playerPosX=j;
						playerPosY=i;
						map[i][j]=0;
					}
					if (map[i][j] == GameObjectID.TARGET.ID) {
						maxCnt++;
					}
				}
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		if ( cnt== maxCnt) {
			game.enterState(stageIndex+1);
		}
	}
}