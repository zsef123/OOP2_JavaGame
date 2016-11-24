package gameStates;

import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import controller.CrossFadeTransition;
import controller.GameObjectID;
import controller.GameStateID;
import models.Ball;
import models.GameObject;
import models.Player;
import models.Target;
import models.UpLadder;
import models.Wall;

public class Stage6 extends Stage {

	public Stage6(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		this.game=sbg;
		objs= new HashMap<Integer, GameObject>();
		
		objs.put(GameObjectID.WALL.ID, new Wall(GameObjectID.WALL.ID,"stg5_wall"));
		objs.put(GameObjectID.PLAYER1.ID, new Player(GameObjectID.PLAYER1.ID,"stg5_player"));
		
		objs.put(GameObjectID.BALL.ID, new Ball(GameObjectID.BALL.ID,"stg5_ball"));
		objs.put(GameObjectID.BALL2.ID, new Ball(GameObjectID.BALL2.ID,"stg5_ball2"));
		objs.put(GameObjectID.BALL3.ID, new Ball(GameObjectID.BALL3.ID,"stg5_ball3"));
		
		objs.put(GameObjectID.TARGET.ID, new Target(GameObjectID.TARGET.ID,"stg5_target"));
		objs.put(GameObjectID.FILLEDTARGET.ID, new Target(GameObjectID.FILLEDTARGET.ID,"stg5_filledtarget"));
		objs.put(GameObjectID.TARGET2.ID, new Target(GameObjectID.TARGET2.ID,"stg5_target2"));
		objs.put(GameObjectID.FILLEDTARGET2.ID, new Target(GameObjectID.FILLEDTARGET.ID,"stg5_filledtarget2"));
		objs.put(GameObjectID.TARGET3.ID, new Target(GameObjectID.TARGET3.ID,"stg5_target3"));
		objs.put(GameObjectID.FILLEDTARGET3.ID, new Target(GameObjectID.FILLEDTARGET3.ID,"stg5_filledtarget3"));
	
		
		mapInit();
		moveInit();
	}

}
