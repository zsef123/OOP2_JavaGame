package models;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import controller.GameObjectID;

public class Target2 extends GameObject {
	protected Image targetImage;
	public Target2(BasicGame nowScene, int tag, String name) {
		super(nowScene, tag, name);
		// TODO Auto-generated constructor stub
	}

	public Target2(int tag, String name) throws SlickException {
		super(tag, name);
		// TODO Auto-generated constructor stub
		if (tag==GameObjectID.TARGET2.ID) 
			targetImage=new Image("C:\\javaProject\\JavaModels\\target2.png");
		else if (tag ==GameObjectID.FILLEDTARGET2.ID ) 
			targetImage=new Image("C:\\javaProject\\JavaModels\\FilledTarget2.png");
	}

	@Override
	public void setPos(int x, int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return targetImage;
	}

}
