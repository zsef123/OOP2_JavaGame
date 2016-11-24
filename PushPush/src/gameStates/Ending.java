package gameStates;

import java.util.Vector;
import java.awt.Font;

import org.newdawn.slick.*;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import controller.*;
import controller.Ranking.RankEle;
import models.Player;

public class Ending extends BasicGameState {
	StateBasedGame game;
	GameContainer gc;
	
	private SpriteSheet bgSprite;
	private Animation ani;
	protected int ID=GameStateID.ENDING.ID;
	
	private Player backGroundChar[];
	private Font font;
	private TrueTypeFont ttFont;
	private TextField inputNickname;
	private Image inputButton;
	private Image inputButtonNormal;
	private Image inputButtonOnClick;
	private Image cancelButtonNormal;
	private Image cancelButtonOnClick;
	private Image cancelButton;
	private Ranking rank;
	private String rankList;
	
	public Ending(int id) throws SlickException {
		// TODO Auto-generated constructor stub
		this.ID = id;
		System.out.println("ENDING ID:"+ID);

		bgSprite=new SpriteSheet("C:\\javaProject\\JavaModels\\Ending_Background_Image.png",Main.WIDTH,Main.HEIGHT);
		ani= new Animation(bgSprite,200);
		
		inputButtonNormal=new Image("C:\\javaProject\\JavaModels\\inputButton.png");
		inputButtonOnClick=new Image("C:\\javaProject\\JavaModels\\inputButtonOnClick.png");
		inputButton=inputButtonNormal;
		
		cancelButtonNormal=new Image("C:\\javaProject\\JavaModels\\cancelButton.png");
		cancelButtonOnClick=new Image("C:\\javaProject\\JavaModels\\cancelButtonOnClick.png");
		cancelButton=cancelButtonNormal;
		
		rankList="";
		backGroundChar= new Player[20];
		for ( int i=0;i<20;i++) {
			int j=1;
			if( i%2 ==0 ) j=11;
			backGroundChar[i]=new Player(j,"Ending_background");
			int direction = (int) (Math.random()*3) ;
			backGroundChar[i].setDirection(Direction.fromInt(direction));
			int randomPosX = (int)(Math.random()*Main.WIDTH);
			int randomPosY = (int)(Math.random()*Main.HEIGHT);
			backGroundChar[i].setPos( randomPosX, randomPosY);
		}
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		// TODO Auto-generated method stub
		this.gc=gc;
		this.game=sbg;
		
		rank=Ranking.getInstance();

		font= new Font("모리스9", Font.BOLD, 24);
		ttFont = new TrueTypeFont(font , false);
		inputNickname= new TextField(gc, ttFont, 100,120, 140, 50);
		inputNickname.setFocus(false);
	}

	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {			
		inputNickname.setFocus(true);
		inputNickname.setCursorVisible(true);
		inputNickname.setMaxLength(5);
		inputNickname.setBorderColor(Color.red);
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

	private void drawStringByLine(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n")) {
        	g.setColor(new Color(77,193,241));
        	for (String str : line.split("\t")) {
                g.drawString(str, x, y);
                x+=100;
                g.setColor(new Color(244,219,160));
        	}
        	x-=200;
        	y+=30;
        }
    }
	public void backGroundPlayerMoveRender() {
		for (int i=0;i<20;i++) {
			int x= backGroundChar[i].getX();
			int y= backGroundChar[i].getY();
			backGroundChar[i].getAnimation().draw(x, y);;
			Direction dir= Direction.fromInt(backGroundChar[i].getDirectionInt());
			switch(dir) {
			case DOWN:
				if ( y > Main.HEIGHT) 
					y=(int)(Math.random()*Main.HEIGHT);
				backGroundChar[i].setPos(x, y+1);
				break;
			case UP:
				if ( y < 0)
					y=(int)(Math.random()*Main.HEIGHT);
				backGroundChar[i].setPos(x, y-1);
				break;
			case LEFT:
				if ( x < 0 )
					x= (int)(Math.random()*Main.WIDTH);
				backGroundChar[i].setPos(x-1, y);
				break;
			case RIGHT:
				if ( x > Main.WIDTH) 
					x= (int)(Math.random()*Main.WIDTH);
				backGroundChar[i].setPos(x+1, y);
				break;
			}
		}
	}
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.setColor(Color.white);
		g.setFont(ttFont);
		ani.draw();
		inputNickname.render(gc, g);
		//얘도 변수화
		g.drawImage(inputButton,70,200);
		g.drawImage(cancelButton,170,200);
		
		drawStringByLine(g,rankList,370,115);
		
		backGroundPlayerMoveRender();
		//g.drawString(rankList, 420, 135);
		// mouse cursor
		Input in=gc.getInput();
		int xpos=in.getMouseX();
		int ypos=in.getMouseY();
		g.drawString(Integer.toString(xpos), 10, 20);
		g.drawString(Integer.toString(ypos), 10, 40);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		// TODO Auto-generated method stub
			
	}
	@Override
	public void mousePressed(int button, int x, int y) {
		
		if ( (x > 82 && x < 146 ) && (y > 218 && y < 284 ) ) {
			if (button == Input.MOUSE_LEFT_BUTTON ) {
				String nickName= inputNickname.getText();
				System.out.println("button click");
				rank.writeRank(nickName);
				rankList=rankRender();
				inputButton=inputButtonOnClick;
			}
		}
		else if ( (x > 192 && x < 246 ) && (y > 219 && y < 275 ) ) {
			if (button == Input.MOUSE_LEFT_BUTTON ) {
					inputNickname.setText("");
					System.out.println("cancel click");
					cancelButton=cancelButtonOnClick;
				}	
		}
		
	}
	@Override
	public void mouseReleased(int button, int x,int y) {
		if ( (x > 82 && x < 146 ) && (y > 218 && y < 284 ) ) {
				if (button == Input.MOUSE_LEFT_BUTTON ) {
					inputButton=inputButtonNormal;
				}
			}
		else if ( (x > 192 && x < 246 ) && (y > 219 && y < 275 ) ) {
			if (button == Input.MOUSE_LEFT_BUTTON ) {
					cancelButton=cancelButtonNormal;
				}	
		}
	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.ID;
	}

}
