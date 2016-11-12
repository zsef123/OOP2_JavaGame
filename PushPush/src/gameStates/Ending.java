package gameStates;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import controller.GameStateID;

public class Ending extends BasicGameState {
	StateBasedGame game;
	GameContainer gc;
	
	private Image img;
	protected int ID=GameStateID.ENDING.ID;
	
	public Ending(int id) throws SlickException {
		// TODO Auto-generated constructor stub
		this.ID = id;
		System.out.println("ENDING ID:"+ID);
		// change image
		this.img=new Image("C:\\javaProject\\JavaModels\\Ending_Backgroud_Image.png");
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		this.gc=gc;
		this.game=sbg;
	}
	private void getAllScore() {
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.drawImage(img,0,0);
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

}
