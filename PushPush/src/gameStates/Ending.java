package gameStates;

import java.util.Vector;

import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import controller.GameStateID;
import controller.Main;
import controller.Ranking;
import controller.Ranking.RankEle;

public class Ending extends BasicGameState {
	StateBasedGame game;
	GameContainer gc;
	
	private SpriteSheet bgSprite;
	private Animation ani;
	protected int ID=GameStateID.ENDING.ID;
	
	Font font;
	private TextField inputNickname;
	private Image inputButton;
	private Image cancelButton;
	private Ranking rank;
	private String rankList;
	public Ending(int id) throws SlickException {
		// TODO Auto-generated constructor stub
		this.ID = id;
		System.out.println("ENDING ID:"+ID);

		bgSprite=new SpriteSheet("C:\\javaProject\\JavaModels\\Ending_Background_Image.png",Main.WIDTH,Main.HEIGHT);
		ani= new Animation(bgSprite,200);
		inputButton=new Image("C:\\javaProject\\JavaModels\\inputButton.png");
		cancelButton=new Image("C:\\javaProject\\JavaModels\\cancelButton.png");
		rankList="";
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		this.gc=gc;
		this.game=sbg;
		
		rank=Ranking.getInstance();
		font = new TrueTypeFont(new java.awt.Font(java.awt.Font.SERIF,java.awt.Font.BOLD , 26), false);
		//�̰͵� �� ����ȭ �ؾ� �ؿ��� ����� �ִ�.
		inputNickname= new TextField(gc, font, 100,120, 200, 50);
		inputNickname.setCursorVisible(true);
		inputNickname.setFocus(false);
		inputNickname.setMaxLength(5);
		inputNickname.setBorderColor(Color.red);
	}

	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		inputNickname.setFocus(true);
	}
	private String rankRender() {
		Vector<RankEle> rankMap=rank.getRankFile();
		String line="Name\tScore\n";
		int i=0;
		for (RankEle e: rankMap ) {
			line+= e.name+"\t"+ Integer.toString(e.score)+"\n";
			if (i++==9) break;
		}		
		System.out.println(line);
		return line;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		ani.draw();
		inputNickname.render(gc, g);
		//�굵 ����ȭ
		g.drawImage(inputButton,70,200);
		g.drawImage(cancelButton,170,200);
		g.drawString(rankList, 500, 50);
		// mouse cursor
		
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
