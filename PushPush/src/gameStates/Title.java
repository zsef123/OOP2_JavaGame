package gameStates;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import controller.AudioPlayer;
import controller.Main;

public class Title extends BasicGameState {
	StateBasedGame game;
	GameContainer gc;
	
	private AudioPlayer audio;
	
	private SpriteSheet sp;
	private Animation ani;
	
	private Image logo;
	// 나중에 ID는 Enum으로 관리
	protected int ID=0;
	
	public Title(int id) throws SlickException {
		// TODO Auto-generated constructor stub
		this.ID = id;
		System.out.println("TITLE ID:"+ID);	

		logo=new Image("C:\\javaProject\\JavaModels\\logo.png");
		sp= new SpriteSheet("C:\\javaProject\\JavaModels\\Title_Backgroud_Image3.png",Main.WIDTH,Main.HEIGHT);
		this.ani=new Animation(sp,250);
		audio=AudioPlayer.getInstance();
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		this.gc=gc;
		this.game=sbg;
		audio.bgmPlay(true);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		//g.drawImage(img, 0, 0);
		ani.draw();
		logo.draw();
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
		case Input.KEY_SPACE:
			// MainFrame으로
			game.enterState(1);
			break;
		case Input.KEY_2:
			game.enterState(51);
		}
		
	}
}
