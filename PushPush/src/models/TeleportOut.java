package models;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TeleportOut extends GameObject {

	protected Image TeleportImage;
	public TeleportOut(BasicGame nowScene, int tag, String name) {
		super(nowScene, tag, name);
		// TODO Auto-generated constructor stub
	}

	public TeleportOut(int tag, String name) throws SlickException {
		super(tag, name);
		// TODO Auto-generated constructor stub
		TeleportImage= new Image("C:\\javaProject\\JavaModels\\objects\\tpout.png");
	}

	@Override
	public void setPos(int x, int y) {
		// TODO Auto-generated method stub
		this.posX=x;
		this.posY=y;
	}
	public int getX() {
		return this.posX;
	}
	public int getY() {
		return this.posX;
	}
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return TeleportImage;
	}

}
