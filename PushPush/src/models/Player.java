package models;

import org.newdawn.slick.Animation;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import controller.CustomAnimation;
import controller.Direction;
import controller.GameObjectID;

public class Player extends GameObject {
	protected SpriteSheet playerImage[];
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
		playerImage= new SpriteSheet[4];
		if (tag==GameObjectID.PLAYER1.ID) {
			playerImage[0] = new SpriteSheet("C:\\javaProject\\JavaModels\\objects\\playerDownSheet.png", IMGWIDTH, IMGHEIGHT);
			playerImage[1] = new SpriteSheet("C:\\javaProject\\JavaModels\\objects\\playerUpSheet.png", IMGWIDTH, IMGHEIGHT);
			playerImage[2] = new SpriteSheet("C:\\javaProject\\JavaModels\\objects\\playerLeftSheet.png", IMGWIDTH, IMGHEIGHT);
			playerImage[3] = new SpriteSheet("C:\\javaProject\\JavaModels\\objects\\playerRightSheet.png", IMGWIDTH, IMGHEIGHT);
		}
		else if (tag==GameObjectID.PLAYER2.ID) {
			playerImage[0] = new SpriteSheet("C:\\javaProject\\JavaModels\\objects\\player2DownSheet.png", IMGWIDTH, IMGHEIGHT);
			playerImage[1] = new SpriteSheet("C:\\javaProject\\JavaModels\\objects\\player2UpSheet.png", IMGWIDTH, IMGHEIGHT);
			playerImage[2] = new SpriteSheet("C:\\javaProject\\JavaModels\\objects\\player2LeftSheet.png", IMGWIDTH, IMGHEIGHT);
			playerImage[3] = new SpriteSheet("C:\\javaProject\\JavaModels\\objects\\player2RightSheet.png", IMGWIDTH, IMGHEIGHT);
		}
		playerAni = new Animation[4];
		for (int i=0;i<4;i++)
			playerAni[i]=new Animation(playerImage[i],1000);
		
		testAni=new CustomAnimation("C:\\javaProject\\JavaModels\\objects\\player2.png",16,16,3,10);
	}
	public void setDirection(Direction direction) {
		this.direction=direction.ID;
	}
	public int getDirectionInt() {
		return direction;
	}
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
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
		this.posX=x;
		this.posY=y;
	}

}
