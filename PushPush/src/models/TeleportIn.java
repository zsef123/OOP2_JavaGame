package models;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class TeleportIn extends GameObject {
	protected Image TeleportImage;
	public TeleportIn(BasicGame nowScene, int tag, String name) {
		super(nowScene, tag, name);
		// TODO Auto-generated constructor stub
	}

	public TeleportIn(int tag, String name) throws SlickException {
		super(tag, name);
		// TODO Auto-generated constructor stub
		TeleportImage = new Image("C:\\javaProject\\JavaModels\\objects\\tpin.png");
	}

	@Override
	public void setPos(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return TeleportImage;
	}

}
