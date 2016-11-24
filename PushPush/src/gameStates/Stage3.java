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
import models.Target2;
import models.Target3;
import models.TeleportIn;
import models.TeleportOut;
import models.Wall;

public class Stage3 extends Stage {

	public Stage3(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub

		this.game=sbg;
		// TODO Auto-generated method stub
		objs=new HashMap<Integer,GameObject>();
		
		objs.put(GameObjectID.WALL.ID, new Wall(GameObjectID.WALL.ID,"stg3_wall"));
		objs.put(GameObjectID.PLAYER1.ID, new Player(GameObjectID.PLAYER1.ID,"stg3_player"));
		
		objs.put(GameObjectID.BALL.ID, new Ball(GameObjectID.BALL.ID,"stg3_ball"));
		objs.put(GameObjectID.BALL2.ID, new Ball(GameObjectID.BALL2.ID,"stg3_ball"));
		objs.put(GameObjectID.BALL3.ID, new Ball(GameObjectID.BALL3.ID,"stg3_ball"));
		
		objs.put(GameObjectID.TARGET.ID, new Target(GameObjectID.TARGET.ID,"stg3_target"));
		objs.put(GameObjectID.FILLEDTARGET.ID, new Target(GameObjectID.FILLEDTARGET.ID,"stg3_filledtarget"));
		objs.put(GameObjectID.TARGET2.ID, new Target2(GameObjectID.TARGET2.ID,"stg3_target"));
		objs.put(GameObjectID.FILLEDTARGET2.ID, new Target2(GameObjectID.FILLEDTARGET2.ID,"stg3_filledtarget"));
		objs.put(GameObjectID.TARGET3.ID, new Target3(GameObjectID.TARGET3.ID,"stg3_target"));
		objs.put(GameObjectID.FILLEDTARGET3.ID, new Target3(GameObjectID.FILLEDTARGET3.ID,"stg3_filledtarget"));
		
		objs.put(GameObjectID.TELEPORTIN.ID, new TeleportIn(GameObjectID.TELEPORTIN.ID,"stg3_teleportin"));
		objs.put(GameObjectID.TELEPORTOUT.ID, new TeleportOut(GameObjectID.TELEPORTOUT.ID,"stg3_teleportout"));
		
		mapInit();
		moveInit();
	}

}
