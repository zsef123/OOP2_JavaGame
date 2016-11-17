package models;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Wall extends GameObject {
	private Image sprite;
	
	public Wall(BasicGame nowScene, int tag, String name) {
		super(nowScene, tag, name);
		// TODO Auto-generated constructor stub
	}

	public Wall(int tag, String name) throws SlickException {
		super(tag, name);
		// TODO Auto-generated constructor stub
		sprite=new Image("C:\\javaProject\\JavaModels\\objects\\wall.png");
	}

	@Override
	public void setPos(int x, int y) {
		// TODO Auto-generated method stub
		this.posX=x;
		this.posY=y;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return sprite;
	}

}
