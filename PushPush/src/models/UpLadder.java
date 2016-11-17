package models;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class UpLadder extends GameObject {
	protected Image upLadderImage;
	public UpLadder(BasicGame nowScene, int tag, String name) {
		super(nowScene, tag, name);
		// TODO Auto-generated constructor stub
	}

	public UpLadder(int tag, String name) throws SlickException {
		super(tag, name);
		// TODO Auto-generated constructor stub
		upLadderImage=new Image("C:\\javaProject\\JavaModels\\objects\\ladder.png");
	}

	@Override
	public void setPos(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return upLadderImage;
	}

}
