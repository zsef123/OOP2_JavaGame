package models;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

abstract public class GameObject {
	protected BasicGame scene;
	protected Shape rect;
	public int posX,posY;
	// use name on Unity
	// tag means type of gameobjcet( like a player, just wall , unmoveable ,... etc)
	protected int tag;
	protected String name;
	
	
	public GameObject(BasicGame nowScene,int tag, String name) {
		this.scene=nowScene;
		this.tag=tag;
		this.name=name;
	}
	public GameObject(int tag, String name) {
		this.tag=tag;
		this.name=name;
	}
	abstract public void setPos(int x, int y);
	abstract public Image getImage();
	public int getX() {
		// TODO Auto-generated method stub
		return this.posX;
	}
	public int getY() {
		// TODO Auto-generated method stub
		return this.posY;
	}
}
