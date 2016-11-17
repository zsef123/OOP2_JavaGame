package gameStates;

import java.util.*;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import controller.GameStateID;
import controller.Ranking;
import controller.Ranking.RankEle;

public class Ending extends BasicGameState {
	StateBasedGame game;
	GameContainer gc;
	
	private Image img;
	protected int ID=GameStateID.ENDING.ID;
	
	Font font;
	private TextField inputNickname;
	private Image inputButton;
	private Ranking rank;
	private String rankList;
	public Ending(int id) throws SlickException {
		// TODO Auto-generated constructor stub
		this.ID = id;
		System.out.println("ENDING ID:"+ID);
		// change image
		this.img=new Image("C:\\javaProject\\JavaModels\\Ending_Backgroud_Image.png");
		inputButton=new Image("C:\\javaProject\\JavaModels\\inputButton.png");
		rankList="";
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		this.gc=gc;
		this.game=sbg;
		
		rank=Ranking.getInstance();
		font = new TrueTypeFont(new java.awt.Font(java.awt.Font.SERIF,java.awt.Font.BOLD , 26), false);
		//이것도 다 변수화 해야 밑에거 맞출수 있다.
		inputNickname= new TextField(gc, font, 50,50, 200, 50);
		inputNickname.setCursorVisible(true);
		inputNickname.setMaxLength(5);
	}
	private String rankRender() {
		Vector<RankEle> rankMap=rank.getRankFile();
		String line="Name\tScore\n";
		for (RankEle e: rankMap ) {
			line+= e.name+"\t"+ Integer.toString(e.score)+"\n";
		}
		
		System.out.println(line);
		return line;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.drawImage(img,0,0);
		inputNickname.render(gc, g);
		//얘도 변수화
		g.drawImage(inputButton,50,200);
		g.drawString(rankList, 500, 50);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		// TODO Auto-generated method stub
			
	}
	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) {
		if ( (x > 200 && x < 400 )
			&& (y > 200 && y < 250 ) ) {
			if (button == Input.MOUSE_LEFT_BUTTON ) {
				String nickName= inputNickname.getText();
				System.out.println("button click");
				rank.writeRank(nickName);
				rankList=rankRender();
			}
		}
	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.ID;
	}

}
