package models;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Target extends GameObject {
	protected Image targetImage;
	public Target(BasicGame nowScene, int tag, String name) {
		super(nowScene, tag, name);
		// TODO Auto-generated constructor stub
	}

	public Target(int tag, String name) throws SlickException {
		super(tag, name);
		// TODO Auto-generated constructor stub
		if (tag==3) 
			targetImage=new Image("C:\\javaProject\\JavaModels\\target.png");
		else if (tag ==5 ) 
			targetImage=new Image("C:\\javaProject\\JavaModels\\FilledTarget.png");
	}

	@Override
	public void setPos(float x, float y) {
		// TODO Auto-generated method stub

	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return targetImage;
	}

}
