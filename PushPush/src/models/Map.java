package models;

import java.util.Vector;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class Map extends GameObject {
	protected Image bgImage;
	protected Vector<GameObject> node;
	public Map(BasicGame nowScene, int tag, String name) {
		super(nowScene, tag, name);
		// TODO Auto-generated constructor stub
	}

	public Map(int tag, String name) throws SlickException {
		super(tag, name);
		// TODO Auto-generated constructor stub
		bgImage=new Image("C:\\javaProject\\JavaModels\\pencils.png");
		node = new Vector<GameObject>();
	}
	public void drawObjects() {
		for (GameObject n : node) {
			n.getImage().draw( n.posX, n.posY, 20, 20);
		}
	}
	// 여기에 플레이어, 벽 추가
	public abstract void mapSetting() throws SlickException ;
	@Override
	public void setPos(float x, float y) {
		// TODO Auto-generated method stub
		posX=0;
		posY=0;
	}
	public Vector<GameObject> getNode() {
		return node;
	}
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return bgImage;
	}

}
