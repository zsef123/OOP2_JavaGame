package models;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Ball extends GameObject {
	protected Image sprite;
	public Ball(BasicGame nowScene, int tag, String name) {
		super(nowScene, tag, name);
		// TODO Auto-generated constructor stub
	}

	public Ball(int tag, String name) throws SlickException {
		super(tag, name);
		// TODO Auto-generated constructor stub
		sprite=new Image("C:\\javaProject\\JavaModels\\ball.png");
	}

	@Override
	public void setPos(int x, int y) {
		// TODO Auto-generated method stub
		posX=x;
		posY=y;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return sprite;
	}
	public void collision() {
		// how?
		posX++;
		posY++;
	}

}
