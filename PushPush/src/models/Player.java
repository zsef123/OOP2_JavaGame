package models;

import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Player extends GameObject {
	protected Image playerImage;
	protected SpriteSheet playerImage2;
	protected Animation playerAni;
	
	private final int IMGWIDTH=16;
	private final int IMGHEIGHT=16;
	public Player(BasicGame nowScene, int tag, String name) {
		super(nowScene, tag, name);
		// TODO Auto-generated constructor stub
	}

	public Player(int tag, String name) throws SlickException {
		super(tag, name);
		// TODO Auto-generated constructor stub
		playerImage = new Image("C:\\javaProject\\JavaModels\\objects\\player.png");
		playerImage2 = new SpriteSheet("C:\\javaProject\\JavaModels\\objects\\player2.png", IMGWIDTH, IMGHEIGHT);
		playerAni = new Animation(playerImage2, 1000);
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return playerImage;
	}
	public Animation getAnimation() {
		return playerAni;
		
	}

	@Override
	public void setPos(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
