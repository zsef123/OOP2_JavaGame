package gameStates;

import java.util.EmptyStackException;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.*;

import controller.*;

public class Pause extends BasicGameState {

	StateBasedGame game;
	GameContainer gc;
	private AudioPlayer audio;

	protected Image bgImage;
	protected SpriteSheet bgSprite;
	protected Animation bgAnimation;
	
	private Image pauseIcon;
	
	private Image volumeMeter;
	private Image volumeSelector;
	private float volumeSelectorLocate;
	protected int ID=200;
	public Pause(int id) throws SlickException {
		this.ID=id;
		audio=AudioPlayer.getInstance();
		// TODO Auto-generated constructor stub
		pauseIcon=new Image("C:\\javaProject\\JavaModels\\pauseIcon.png");
		volumeMeter=new Image("C:\\javaProject\\JavaModels\\volumeMeter.png");
		volumeSelector=new Image("C:\\javaProject\\JavaModels\\volumeSelector.png");
		bgSprite=new SpriteSheet("C:\\javaProject\\JavaModels\\Pause_Background_Image2.png",Main.WIDTH,Main.HEIGHT);
		bgAnimation=new Animation(bgSprite, 100);
		bgAnimation.setPingPong(true);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		this.gc=gc;
		this.game=sbg;
	}
	@Override
	public void enter(GameContainer container, StateBasedGame sbg) throws SlickException {
		audio.bgmPlay(false);
		volumeSelectorLocate=audio.getBgmVolume()*190+225;
	}

	@Override
	public void leave(GameContainer container, StateBasedGame sbg) throws SlickException {
		audio.bgmPlay(true);
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		bgAnimation.draw();
		pauseIcon.draw(220,45);
		volumeMeter.draw(170,150);
		volumeSelector.draw(volumeSelectorLocate,150);
		

		Input input = gc.getInput();
		int xpos = input.getMouseX();
		g.drawString(Integer.toString(xpos), 10, 100);
		int ypos = input.getMouseY();
		g.drawString(Integer.toString(ypos), 10, 120);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		// TODO Auto-generated method stub
		Input input = gc.getInput();
		if (Mouse.isButtonDown(0)) {
			int xpos = input.getMouseX();
			int ypos = input.getMouseY();
			if ( ypos >= 120 && ypos <= 180) {
				if ( xpos >=225 && xpos <=415) {
					volumeSelectorLocate=xpos;
					//to 0~1 normalize
					float tmpVolume=(xpos-225)/190.0f;
					System.out.println("clicked volume:"+tmpVolume);
					audio.setBgmVolume(tmpVolume);
				}
			}
		}
	}

	public void keyPressed(int key, char code) {
		if (key==Input.KEY_ESCAPE) {
			// use defalut transition
			game.enterState(Stage.currentStageID, new FadeOutTransition(), new FadeInTransition());
		}
	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return GameStateID.PAUSE.ID;
	}

}
