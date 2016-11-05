package models;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Image;

abstract public class GameObject {
	protected BasicGame scene;
	public float posX,posY;
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
	abstract public void setPos(float x, float y);
	abstract public Image getImage();
}
