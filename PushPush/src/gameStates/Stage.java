package gameStates;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import controller.*;
import models.*;

public abstract class Stage extends BasicGameState {
	StateBasedGame game;
	GameContainer gc;
	protected int ID;
	protected final int FINISHED=5;
	protected final int FINISHED2=52;
	// for option
	protected static int currentStageID;
	protected int stageIndex;
	protected Image bgImage;
	protected SpriteSheet bgSprite;
	protected Animation bgAnimation;
	protected HashMap<Integer,GameObject> objs;
	protected String fileDir="C:\\javaProject\\JavaModels\\Stages\\";
	// 그려줄 맵
	protected int moveCount;
	protected int time;
	protected int targetCount;
	protected int resetCount=3;
	protected int maxTargetCount;
	
	protected Stack<Undo> undoStack;
	protected int saveUndoIndex;
	protected int getUndoIndex;
	protected int playerPosX;
	protected int playerPosY;
	
	protected int player2PosX;
	protected int player2PosY;
	
	public int[][] map;
	public final int mapWidth=20;
	public final int mapHeight=15;
	
	private Clock clock;
	private Image[] clockImages;
	private Animation[] clockSprites;
	
	private Font font;
	private TrueTypeFont uniFont;
	
	private PlayerMove move;
	protected Ranking rank;
	public Stage(int id) {
		// TODO Auto-generated constructor stub
		this.ID=id;
		targetCount=0;
		saveUndoIndex=-1;
		getUndoIndex=0;
		rank= Ranking.getInstance();
	}

	@Override
	abstract public void init(GameContainer gc, StateBasedGame sbg) throws SlickException;
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		currentStageID=this.ID;
	}
	public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}
	protected void moveInit() {
		move= PlayerMove.getInstance();
		
		// set zero가 안된다
		move.setZeroMoveCount();
		move.setZeroTargetCount();
		
		move.setPos(playerPosX, playerPosY);
		move.setPos2(player2PosX, player2PosY);
		if ( objs.get(GameObjectID.TELEPORTOUT.ID) != null ) {
			move.setTeleportPos(objs.get(GameObjectID.TELEPORTOUT.ID).posX, objs.get(GameObjectID.TELEPORTOUT.ID).posY);
		}
	}
	public void mapInit()   {
		// TODO Auto-generated method stub
		try {
			bgImage=new Image("C:\\javaProject\\JavaModels\\Game_Background_Image.png");
			bgSprite=new SpriteSheet("C:\\javaProject\\JavaModels\\Game_Background_Image2.png",Main.WIDTH,Main.HEIGHT);
			bgAnimation=new Animation(bgSprite, 100);
		} catch (SlickException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		targetCount=0;
		maxTargetCount=0;
		map=new int[mapWidth][mapHeight];
		try {
			// Stage(ID).txt
			String fileName=fileDir+"Stage"+Integer.toString(ID)+".txt";
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			for( int i=0; i<mapWidth;i++) {
				String line=br.readLine();
				if ( line == null) break;
				String[] lineSplit= line.split("\t");
				for( int j=0;j<mapHeight; j++) {
					map[i][j] = Integer.parseInt(lineSplit[j]) ;
					if ( map[i][j] == GameObjectID.PLAYER1.ID ) {
						playerPosX=j;
						playerPosY=i;
						map[i][j]=0;
					}
					else if ( map[i][j] == GameObjectID.PLAYER2.ID ) {
						player2PosX=j;
						player2PosY=i;
						map[i][j]=0;
					}
					else if (map[i][j] == GameObjectID.TARGET.ID || map[i][j] == GameObjectID.TARGET2.ID 
							|| map[i][j] == GameObjectID.TARGET3.ID) {
						maxTargetCount++;
					}
					else if ( map[i][j] == GameObjectID.TELEPORTOUT.ID) {
						objs.get(GameObjectID.TELEPORTOUT.ID).setPos(j , i);
					}
				}
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		undoStack=new Stack<Undo>();
		clock = Clock.getInstance();
		clockImages=clock.getFlip();
		
		font=new Font("Tempus Sans ITC",Font.PLAIN,26);
		uniFont= new TrueTypeFont(font ,false);
	}
	protected void allInit() {
		mapInit();
		moveInit();
	}
	private void mapRender(int WidthPixel, int HeightPixel) {
		for (int i=0;i<mapWidth; i++) {
			for( int j=0; j<mapHeight; j++) {
				if ( map[i][j] != 0 ) {
					Image img=objs.get(map[i][j]).getImage();
					img.draw(((float)j*WidthPixel)+10, ((float)i*HeightPixel)+5);
				}
			}
		}
	}
	private void UIRender(int Width, int Height) {
		//The unit of measuring time is millisecond
		clock.setTime(time/1000);
		clockSprites=clock.getSprite();
		for (int i=0; i<4;i++) {
			// 51 is give blank between images
			clockImages[i].draw(Width+51*i, Height , 50, 50);
			clockSprites[i].draw(Width+51*i, Height);
		}
		clockImages=clock.getFlip();
		
	}
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		
		bgAnimation.draw();
		g.setColor(Color.white);
		g.setFont(uniFont);
		g.drawString("Time", 470, 20);
		g.drawString("Move : " + moveCount+"\n" , 450, 150);
		g.drawString("Reset : " + resetCount+"\n" , 450, 220);
		
		mapRender(21,21);
		UIRender(400,50);
		((Player) objs.get(GameObjectID.PLAYER1.ID)).getAnimation().draw(playerPosX*21+10, playerPosY*21+5);
		if ( this.ID > 50)
			((Player) objs.get(GameObjectID.PLAYER2.ID)).getAnimation().draw(player2PosX*21+10, player2PosY*21+5);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		time+=delta;
		
		((Player) objs.get(GameObjectID.PLAYER1.ID)).getAnimation().update(delta);
		if (this.ID > 50)
			((Player) objs.get(GameObjectID.PLAYER2.ID)).getAnimation().update(delta);
		if ( targetCount== maxTargetCount && targetCount > 0 ) {
			rank.appendScore(time/1000,moveCount);
			allInit();
			if ( ID+1 == FINISHED || ID+1== FINISHED2) // only player 1 end, player 2 end 
				resetCount=0;
			else
				game.enterState(ID+1, new splitByPixelTransition(),null);
		}
		if ( resetCount == 0) {
			rank.appendScore(time/1000,moveCount);
			game.enterState(GameStateID.ENDING.ID,new CrossFadeTransition(450),null );
		}
	}
	private void doUndo(Undo undo) {
		playerPosX=undo.getX();
		playerPosY=undo.getY();
		targetCount=undo.getTarget();
		move.setTargetCount(targetCount);
		map=undo.getMap();
	}
	public void keyPressed(int key, char code) {
		System.out.println("Key:"+code+","+key);
		System.out.println("cnt:"+targetCount+" maxcnt:"+maxTargetCount);
		if (code == 'r') {
			System.out.println("reset:"+resetCount);
			resetCount--;
			moveCount=move.setZeroMoveCount();
			targetCount=move.setZeroTargetCount();
			mapInit();
		}
		else if (code == 'z') {
			try {
				doUndo( undoStack.pop() );
			}
			catch (EmptyStackException e) {
				// do nothing
			}
			System.out.println("getundo:"+getUndoIndex);
		}
		else if (key==Input.KEY_ESCAPE) {
			
			game.enterState(GameStateID.PAUSE.ID, null, null);
		}
		else
			playerMove(key);
	}
	private void playerMove(int key) {
		undoStack.push(new Undo(playerPosX, playerPosY, targetCount , map));
		move.setPos(playerPosX, playerPosY);
		int collisionID = 0;
		switch(key) {
		case Input.KEY_LEFT:			
			// game ending count
			((Player) objs.get(GameObjectID.PLAYER1.ID)).setDirection(Direction.LEFT); // use enum
			collisionID=move.leftMove(map, GameObjectID.PLAYER1.ID);
			break;
		case Input.KEY_RIGHT:
			((Player) objs.get(GameObjectID.PLAYER1.ID)).setDirection(Direction.RIGHT);
			collisionID=move.rightMove(map, GameObjectID.PLAYER1.ID);
			break;
		case Input.KEY_UP:
			((Player) objs.get(GameObjectID.PLAYER1.ID)).setDirection(Direction.UP);
			collisionID=move.upMove(map, GameObjectID.PLAYER1.ID);
			break;
		case Input.KEY_DOWN:
			((Player) objs.get(GameObjectID.PLAYER1.ID)).setDirection(Direction.DOWN);
			collisionID=move.downMove(map, GameObjectID.PLAYER1.ID);
			break;
		}
		// case only 2 player use
		if( this.ID > 50 ) {
			move.setPos2(player2PosX, player2PosY);
			switch(key) {
			case Input.KEY_A:
				((Player) objs.get(GameObjectID.PLAYER2.ID)).setDirection(Direction.LEFT);
				collisionID=move.leftMove(map, GameObjectID.PLAYER2.ID);
				break;
			case Input.KEY_D:
				((Player) objs.get(GameObjectID.PLAYER2.ID)).setDirection(Direction.RIGHT);
				collisionID=move.rightMove(map, GameObjectID.PLAYER2.ID);
				break;
			case Input.KEY_W:
				((Player) objs.get(GameObjectID.PLAYER2.ID)).setDirection(Direction.UP);
				collisionID=move.upMove(map, GameObjectID.PLAYER2.ID);
				break;
			case Input.KEY_S:
				((Player) objs.get(GameObjectID.PLAYER2.ID)).setDirection(Direction.DOWN);
				collisionID=move.downMove(map, GameObjectID.PLAYER2.ID);
				break;
			}

			player2PosX=move.get2X();
			player2PosY=move.get2Y();
		}

		playerPosX=move.getX();
		playerPosY=move.getY();
		targetCount=move.getTargetCount();
		moveCount=move.getMoveCount();
	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.ID;
	}

}
