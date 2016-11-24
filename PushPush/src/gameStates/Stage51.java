package gameStates;

import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import controller.GameObjectID;
import models.*;

public class Stage51 extends Stage {

	public Stage51(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		this.game=sbg;
		// TODO Auto-generated method stub
		objs=new HashMap<Integer,GameObject>();
		
		objs.put(GameObjectID.WALL.ID, new Wall(GameObjectID.WALL.ID,"stg51_wall"));
		objs.put(GameObjectID.PLAYER1.ID, new Player(GameObjectID.PLAYER1.ID,"stg51_player"));
		objs.put(GameObjectID.PLAYER2.ID, new Player(GameObjectID.PLAYER2.ID,"stg51_player2"));
		
		objs.put(GameObjectID.BALL.ID, new Ball(GameObjectID.BALL.ID,"stg51_ball"));
		objs.put(GameObjectID.BALL2.ID, new Ball(GameObjectID.BALL2.ID,"stg51_ball2"));
		
		objs.put(GameObjectID.TARGET.ID, new Target(GameObjectID.TARGET.ID,"stg51_target"));
		objs.put(GameObjectID.FILLEDTARGET.ID, new Target(GameObjectID.FILLEDTARGET.ID,"stg51_filledtarget"));
		objs.put(GameObjectID.TARGET2.ID, new Target2(GameObjectID.TARGET2.ID,"stg51_target"));
		objs.put(GameObjectID.FILLEDTARGET2.ID, new Target2(GameObjectID.FILLEDTARGET2.ID,"stg51_filledtarget"));
		
		mapInit();
		moveInit();
	}

}
