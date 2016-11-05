package controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import gameStates.Title;

public class Main extends StateBasedGame {
	// 나중에 변환 가능하게 바꾼다.
    public static int WIDTH   = 500;
    public static int HEIGHT  = 500;
    public static int FPS     = 60;
    
    public Main(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException {
		// TODO Auto-generated method stub
		this.addState(new Title(GameStateID.TITLE.ID));
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			AppGameContainer appgc;
			appgc=new AppGameContainer(new Main("PUSH PUSH - OOP2 GAME PROJECT"));
			appgc.setDisplayMode(WIDTH, HEIGHT, false);
			appgc.setTargetFrameRate(FPS);
			appgc.start();
		}
		catch(SlickException e){
			Logger.getLogger( Main.class.getName() ).log(Level.SEVERE, null ,e);
		}
	}

}
