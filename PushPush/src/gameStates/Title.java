package gameStates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import controller.GameStateID;

public class Title extends BasicGameState {
	StateBasedGame game;
	GameContainer gc;
	
	private Image img;
	// 나중에 ID는 Enum으로 관리
	protected int ID=0;
	
	public Title(int id) throws SlickException {
		// TODO Auto-generated constructor stub
		this.ID = id;
		// change image
		this.img=new Image("C:\\javaProject\\JavaModels\\Title_Backgroud_Image.png");
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		this.gc=gc;
		this.game=sbg;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.drawImage(img, 0, 0);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.ID;
	}
	@Override
	public void keyReleased(int key, char c) {
		switch(key) {
		// Press any key 하면 전환되게
		case Input.KEY_1:
			// MainFrame으로
			game.enterState(1);
			break;
		}
	}
}
