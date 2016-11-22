package controller;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.Transition;

public class splitByPixelTransition implements Transition {
	
	private float pixelSize;
	private float speed;
	private boolean complete;
	public splitByPixelTransition() {
		// TODO Auto-generated constructor stub
		pixelSize=12;
		speed=1.07f;
		complete=false;
	}

	@Override
	public void init(GameState arg0, GameState arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return complete;
	}

	@Override
	public void postRender(StateBasedGame sbg, GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		for (int height = 0; height <= Main.HEIGHT ; height += pixelSize) {
	         for (int width = 0; width <=Main.WIDTH ; width += pixelSize) {
	        	Color color=g.getPixel (width + 1, height + 1);
	            g.setColor(color);
	            g.fillRect (width, height, pixelSize, pixelSize);
	         }
		}
	}

	@Override
	public void preRender(StateBasedGame sbg, GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		// do nothing
	}

	@Override
	public void update(StateBasedGame sbg, GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub
		if (pixelSize < 50 )
			pixelSize *= speed;
		else
			complete=true;
	}

}
