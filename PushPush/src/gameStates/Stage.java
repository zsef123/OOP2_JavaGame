package gameStates;


import java.util.Vector;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import models.*;
public abstract class Stage extends BasicGameState {
	protected int ID;
	protected int stageIndex;
	protected Image bgImage;
	protected Vector<Map> maps;
	public Stage(int id) {
		// TODO Auto-generated constructor stub
		this.ID=id;
	}

	@Override
	abstract public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException;
		

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		// TODO Auto-generated method stub
		//해상도로 바꾼다
		bgImage.draw(0,0,500,500);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.ID;
	}

}
