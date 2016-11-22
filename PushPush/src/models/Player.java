package models;

import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import controller.CustomAnimation;
import controller.Direction;

public class Player extends GameObject {
	protected Image playerImage;
	protected SpriteSheet playerImage2[];
	protected Animation playerAni[];
	
	protected CustomAnimation testAni;
	
	protected int direction; // 0 down 1 up 2 left 3 right
	
	private final int IMGWIDTH=20;
	private final int IMGHEIGHT=20;
	public Player(BasicGame nowScene, int tag, String name) {
		super(nowScene, tag, name);
		// TODO Auto-generated constructor stub
	}

	public Player(int tag, String name) throws SlickException {
		super(tag, name);
		this.direction=0;
		// TODO Auto-generated constructor stub
		playerImage = new Image("C:\\javaProject\\JavaModels\\objects\\player.png");
		playerImage2= new SpriteSheet[4];
		playerImage2[0] = new SpriteSheet("C:\\javaProject\\JavaModels\\objects\\playerDownSheet.png", IMGWIDTH, IMGHEIGHT);
		playerImage2[1] = new SpriteSheet("C:\\javaProject\\JavaModels\\objects\\playerUpSheet.png", IMGWIDTH, IMGHEIGHT);
		playerImage2[2] = new SpriteSheet("C:\\javaProject\\JavaModels\\objects\\playerLeftSheet.png", IMGWIDTH, IMGHEIGHT);
		playerImage2[3] = new SpriteSheet("C:\\javaProject\\JavaModels\\objects\\playerRightSheet.png", IMGWIDTH, IMGHEIGHT);
		playerAni = new Animation[4];
		for (int i=0;i<4;i++)
			playerAni[i]=new Animation(playerImage2[i],1000);
		
		testAni=new CustomAnimation("C:\\javaProject\\JavaModels\\objects\\player2.png",16,16,3,10);
	}
	public void setDirection(Direction direction) {
		this.direction=direction.ID;
	}
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return playerImage;
	}
	public Animation getAnimation() {
		return playerAni[direction];
		
	}
	public CustomAnimation testGetAni() {
		return testAni;
	}
	@Override
	public void setPos(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
