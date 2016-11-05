package models;

import java.util.Vector;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Map extends GameObject {
	protected Image bgImage;
	protected Vector<GameObject> node;
	public Map(BasicGame nowScene, int tag, String name) {
		super(nowScene, tag, name);
		// TODO Auto-generated constructor stub
	}

	public Map(int tag, String name) throws SlickException {
		super(tag, name);
		// TODO Auto-generated constructor stub
		bgImage=new Image("");
		node = new Vector<GameObject>();
	}
	public void drawObjects() {
		for (GameObject n : node) {
			n.getImage().draw( n.posX, n.posY, 20, 20);
		}
	}
	@Override
	public void setPos(float x, float y) {
		// TODO Auto-generated method stub
		posX=0;
		posY=0;
	}

	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return bgImage;
	}

}
