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
		
		objs.put(GameObjectID.WALL.ID, new Wall(GameObjectID.WALL.ID,"stg1_wall"));
		objs.put(GameObjectID.PLAYER1.ID, new Player(GameObjectID.PLAYER1.ID,"stg1_player"));
		objs.put(GameObjectID.BALL.ID, new Ball(GameObjectID.BALL.ID,"stg_ball"));
		objs.put(GameObjectID.TARGET.ID, new Target(GameObjectID.TARGET.ID,"stg1_target"));
		objs.put(GameObjectID.FILLEDTARGET.ID, new Target(GameObjectID.FILLEDTARGET.ID,"stg1_filledtarget"));
		objs.put(GameObjectID.UPLADDER.ID, new UpLadder(GameObjectID.UPLADDER.ID,"stg1_filledtarget"));
		// map[posY][posX]
		mapInit();
		moveInit();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub
		time+=delta;
		if ( targetCount== maxTargetCount && targetCount > 0 ) {
			allInit();
			game.enterState(5, new CrossFadeTransition(450),null);
		}
		if ( resetCount == 0) {
			game.enterState(GameStateID.ENDING.ID);
		}
	}

}
