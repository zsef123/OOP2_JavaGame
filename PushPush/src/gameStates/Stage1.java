package gameStates;

import java.util.Vector;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import models.*;
public class Stage1 extends Stage {
	
	public Stage1(int id) {
		super(id);
		stageIndex=1;
	}

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// TODO Auto-generated method stub
		bgImage=new Image("C:\\javaProject\\JavaModels\\Game_Background_Image.png");
		maps = new Vector<Map>();
	}

}
