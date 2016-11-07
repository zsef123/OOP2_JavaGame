package models;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.SlickException;

public class Map1_1 extends Map {

	public Map1_1(BasicGame nowScene, int tag, String name) {
		super(nowScene, tag, name);
		// TODO Auto-generated constructor stub
	}

	public Map1_1(int tag, String name) throws SlickException {
		super(tag, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mapSetting() throws SlickException {
		// TODO Auto-generated method stub
		node.add(new Wall(12,"wall"));
		node.get(0).setPos(40, 40);
	}

}
