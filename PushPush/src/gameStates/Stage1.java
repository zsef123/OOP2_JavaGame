package gameStates;

import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.*;

import controller.CrossFadeTransition;
import controller.GameObjectID;
import controller.GameStateID;
import controller.splitByPixelTransition;
import models.Ball;
import models.GameObject;
import models.Player;
import models.Target;
import models.Target2;
import models.Wall;
public class Stage1 extends Stage {
	public Stage1(int id) {
		super(id);
		stageIndex=1;
	}
	
	@Override
	// init은 main에서 한번만 불린다 state의 전환과 무관하다.
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
		this.game=sbg;
		this.time=0;
		// TODO Auto-generated method stub
		
		objs=new HashMap<Integer,GameObject>();

		objs.put(GameObjectID.WALL.ID, new Wall(GameObjectID.WALL.ID,"stg1_wall"));
		objs.put(GameObjectID.PLAYER1.ID, new Player(GameObjectID.PLAYER1.ID,"stg1_player"));
		objs.put(GameObjectID.BALL.ID, new Ball(GameObjectID.BALL.ID,"stg_ball"));
		objs.put(GameObjectID.BALL2.ID, new Ball(GameObjectID.BALL.ID,"stg_ball"));
		objs.put(GameObjectID.TARGET.ID, new Target(GameObjectID.TARGET.ID,"stg1_target"));
		objs.put(GameObjectID.FILLEDTARGET.ID, new Target(GameObjectID.FILLEDTARGET.ID,"stg1_filledtarget"));
		objs.put(GameObjectID.TARGET2.ID, new Target2(GameObjectID.TARGET2.ID,"stg2_target2"));
		objs.put(GameObjectID.FILLEDTARGET2.ID, new Target2(GameObjectID.FILLEDTARGET2.ID,"stg2_filledtarget2"));
		// map[posY][posX]
		
		mapInit();
		moveInit();
	}
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		time+=delta;
		
		((Player) objs.get(1)).getAnimation().update(delta);
		if ( targetCount== maxTargetCount && targetCount > 0 ) {
			rank.appendScore(time/1000,moveCount);
			allInit();
			game.enterState(2, new splitByPixelTransition() ,null);
		}
		if ( resetCount == 0) {
			rank.appendScore(time/1000,moveCount);
			game.enterState(GameStateID.ENDING.ID);
		}
	}

}