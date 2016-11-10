package gameStates;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import controller.GameObjectID;
import controller.GameStateID;
import models.Ball;
import models.GameObject;
import models.Player;
import models.Target;
import models.Wall;

public class Stage2 extends Stage {

	public Stage2(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub

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
		
		mapInit();
	}

	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		if ( cnt== maxCnt && cnt > 0) {
			game.enterState(3);
		}
	}
}
