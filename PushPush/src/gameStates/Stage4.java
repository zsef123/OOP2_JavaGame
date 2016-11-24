package gameStates;

import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import controller.*;
import models.*;

public class Stage4 extends Stage {

	public Stage4(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		this.game=sbg;
		// TODO Auto-generated method stub
		objs=new HashMap<Integer,GameObject>();
		
		objs.put(GameObjectID.WALL.ID, new Wall(GameObjectID.WALL.ID,"stg4_wall"));
		objs.put(GameObjectID.PLAYER1.ID, new Player(GameObjectID.PLAYER1.ID,"stg4_player"));
		objs.put(GameObjectID.BALL.ID, new Ball(GameObjectID.BALL.ID,"stg4_ball"));
		objs.put(GameObjectID.TARGET.ID, new Target(GameObjectID.TARGET.ID,"stg4_target"));
		objs.put(GameObjectID.FILLEDTARGET.ID, new Target(GameObjectID.FILLEDTARGET.ID,"stg4_filledtarget"));
		objs.put(GameObjectID.UPLADDER.ID, new UpLadder(GameObjectID.UPLADDER.ID,"stg4_filledtarget"));
		
		mapInit();
		moveInit();
	}

}
