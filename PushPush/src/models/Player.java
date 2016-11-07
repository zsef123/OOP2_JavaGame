package models;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Player extends GameObject {
	protected Image playerImage;
	public Player(BasicGame nowScene, int tag, String name) {
		super(nowScene, tag, name);
		// TODO Auto-generated constructor stub
	}

	public Player(int tag, String name) throws SlickException {
		super(tag, name);
		// TODO Auto-generated constructor stub
		playerImage = new Image("C:\\javaProject\\JavaModels\\player.png");
	}

	@Override
	public void setPos(float x, float y) {
		// TODO Auto-generated method stub

	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return playerImage;
	}

}
