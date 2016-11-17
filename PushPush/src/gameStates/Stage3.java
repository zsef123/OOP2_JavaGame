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
import models.*;

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
		
		objs.put(GameObjectID.WALL.ID, new Wall(GameObjectID.WALL.ID,"stg1_wall"));
		objs.put(GameObjectID.PLAYER1.ID, new Player(GameObjectID.PLAYER1.ID,"stg1_player"));
		objs.put(GameObjectID.BALL.ID, new Ball(GameObjectID.BALL.ID,"stg_ball"));
		objs.put(GameObjectID.BALL2.ID, new Ball(GameObjectID.BALL2.ID,"stg_ball"));
		objs.put(GameObjectID.BALL3.ID, new Ball(GameObjectID.BALL3.ID,"stg_ball"));
		objs.put(GameObjectID.TARGET.ID, new Target(GameObjectID.TARGET.ID,"stg1_target"));
		objs.put(GameObjectID.FILLEDTARGET.ID, new Target(GameObjectID.FILLEDTARGET.ID,"stg1_filledtarget"));
		objs.put(GameObjectID.TARGET2.ID, new Target2(GameObjectID.TARGET2.ID,"stg1_target"));
		objs.put(GameObjectID.FILLEDTARGET2.ID, new Target2(GameObjectID.FILLEDTARGET2.ID,"stg1_filledtarget"));
		objs.put(GameObjectID.TARGET3.ID, new Target3(GameObjectID.TARGET3.ID,"stg1_target"));
		objs.put(GameObjectID.FILLEDTARGET3.ID, new Target3(GameObjectID.FILLEDTARGET3.ID,"stg1_filledtarget"));
		objs.put(GameObjectID.TELEPORTIN.ID, new TeleportIn(GameObjectID.TELEPORTIN.ID,"stg1_TeleIn"));
		objs.put(GameObjectID.TELEPORTOUT.ID, new TeleportOut(GameObjectID.TELEPORTOUT.ID,"stg1_TeleOut"));
		// map[posY][posX]
		mapInit();
		moveInit();
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		time+=delta;
		if ( targetCount== maxTargetCount && targetCount > 0 ) {
			game.enterState(4);
		}
		if ( resetCount == 0) {
			game.enterState(GameStateID.ENDING.ID);
		}
	}

}
