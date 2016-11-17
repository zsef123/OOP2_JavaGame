package gameStates;


import java.io.*;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Stack;
import java.util.Vector;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import models.*;
import controller.GameObjectID;
import controller.GameStateID;
import controller.PlayerMove;
import controller.Ranking;
import controller.Undo;
public abstract class Stage extends BasicGameState {
	StateBasedGame game;
	GameContainer gc;
	protected int ID;
	protected int stageIndex;
	protected static Image bgImage;
	protected static SpriteSheet bgSprite;
	protected static Animation bgAnimation;
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
	private int saveMapValue;
	
	public int[][] map;
	public final int mapWidth=20;
	public final int mapHeight=15;
	
	private Clock clock;
	private Image[] clockImages;
	private Animation[] clockSprites;
	
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
		mapInit();
		moveInit();
	}
	public void leave(GameContainer gc, StateBasedGame sbg) throws SlickException {
		
	}
	protected void moveInit() {
		move= PlayerMove.getInstance();
		
		// set zero가 안된다
		move.setZeroMoveCount();
		move.setZeroTargetCount();
		
		move.setPos(playerPosX, playerPosY);
		if ( objs.get(GameObjectID.TELEPORTOUT.ID) != null ) {
			move.setTeleportPos(objs.get(GameObjectID.TELEPORTOUT.ID).posX, objs.get(GameObjectID.TELEPORTOUT.ID).posY);
		}
	}
	public void mapInit()   {
		// TODO Auto-generated method stub
		try {
			bgImage=new Image("C:\\javaProject\\JavaModels\\Game_Background_Image.png");
			bgSprite=new SpriteSheet("C:\\javaProject\\JavaModels\\Game_Background_Image2.png",640,480);
			bgAnimation=new Animation(bgSprite, 100);
		} catch (SlickException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		targetCount=0;
		maxTargetCount=0;
		map=new int[mapWidth][mapHeight];
		try {
			String fileName=fileDir+"Stage"+Integer.toString(ID)+".txt";
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			for( int i=0; i<mapWidth;i++) {
				String line=br.readLine();
				if ( line == null) break;
				String[] lineSplit= line.split("\t");
				for( int j=0;j<mapHeight; j++) {
					map[i][j] = Integer.parseInt(lineSplit[j]) ;
					if ( map[i][j] == 1 ) {
						playerPosX=j;
						playerPosY=i;
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
		//해상도로 바꾼다
		//bgImage.draw(0,0,640,480);
		bgAnimation.draw();
		g.drawString("Time : " + time/1000, 450, 50);
		g.drawString("Move : " + moveCount , 450, 150);
		g.drawString("Reset : " + resetCount , 450, 200);
		g.drawString("Cnt : " + targetCount , 450, 250);
		mapRender(21,21);
		UIRender(400,50);
		((Player) objs.get(1)).getAnimation().draw(playerPosX*21+10, playerPosY*21+5);
	}

	@Override
	public abstract void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException;
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
			// reset
			System.out.println("reset:"+resetCount);
			// 왜 마이너스가 안될까
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
		else
			playerMove(key);
	}
	private void playerMove(int key) {
		// end
		// how to remove trash move
		undoStack.push(new Undo(playerPosX, playerPosY, targetCount , map));
		move.setPos(playerPosX, playerPosY);
		int collisionID = 0;
		switch(key) {
		case Input.KEY_LEFT:			
			// game ending count
			collisionID=move.leftMove(map);
			break;
		case Input.KEY_RIGHT:
			collisionID=move.rightMove(map);
			break;
		case Input.KEY_UP:
			collisionID=move.upMove(map);
			break;
		case Input.KEY_DOWN:
			collisionID=move.downMove(map);
			break;
		}
		targetCount=move.getTargetCount();
		moveCount=move.getMoveCount();
		playerPosX=move.getX();
		playerPosY=move.getY();

		//only case if collisionID is items
		
	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.ID;
	}

}
