package gameStates;

import java.util.HashMap;
import controller.*;
import models.*;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Stage5 extends Stage {

	public Stage5(int id) {
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
		
		objs.put(GameObjectID.TELEPORTIN.ID, new UpLadder(GameObjectID.TELEPORTIN.ID,"stg5_teleportin"));
		objs.put(GameObjectID.TELEPORTOUT.ID, new UpLadder(GameObjectID.TELEPORTOUT.ID,"stg5_teleportin"));
		
		
		mapInit();
		moveInit();
	}

}
