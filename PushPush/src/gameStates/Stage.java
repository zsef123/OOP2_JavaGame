package gameStates;


import java.io.*;
import java.util.HashMap;
import java.util.Vector;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import models.*;
import controller.GameObjectID;
import controller.GameStateID;
import controller.PlayerMove;
public abstract class Stage extends BasicGameState {
	StateBasedGame game;
	GameContainer gc;
	protected int ID;
	protected int stageIndex;
	protected Image bgImage;
	protected HashMap<Integer,GameObject> objs;
	protected String fileDir="C:\\javaProject\\JavaModels\\Stages\\";
	// 그려줄 맵
	protected int moveCount;
	protected int time;
	protected int targetCount;
	protected int resetCount=3;
	protected int maxTargetCount;
	
	protected int playerPosX;
	protected int playerPosY;
	private int saveMapValue;
	
	public int[][] map;
	public final int mapWidth=20;
	public final int mapHeight=15;
	
	private PlayerMove move;
	public Stage(int id) {
		// TODO Auto-generated constructor stub
		this.ID=id;
		targetCount=0;
	}

	@Override
	abstract public void init(GameContainer gc, StateBasedGame sbg) throws SlickException;
	protected void moveInit() {
		move= PlayerMove.getInstance();
		move.setZeroMoveCount();
		move.setPos(playerPosX, playerPosY);
		if ( objs.get(GameObjectID.TELEPORTOUT.ID) != null ) {
			move.setTeleportPos(objs.get(GameObjectID.TELEPORTOUT.ID).posX, objs.get(GameObjectID.TELEPORTOUT.ID).posY);
		}
	}
	public void mapInit() {
		// TODO Auto-generated method stub
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
					else if (map[i][j] == GameObjectID.TARGET.ID) {
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
	}
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		//해상도로 바꾼다
		bgImage.draw(0,0,640,480);
		g.drawString("Time : " + time/1000, 450, 50);
		g.drawString("Move : " + moveCount , 450, 150);
		for (int i=0;i<mapWidth; i++) {
			for( int j=0; j<mapHeight; j++) {
				GameObjectID value=GameObjectID.fromInt(map[i][j]);
				
				// objs.get(map[i][j].getImage() 로하면 완벽하다
				if ( map[i][j] != 0 ) {
					Image img=objs.get(map[i][j]).getImage();
					img.draw(((float)j*20)+10, ((float)i*20)+5);
				}
			}
		}
		((Player) objs.get(1)).getAnimation().draw(playerPosX*20+10, playerPosY*20+5);
	}

	@Override
	public abstract void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException;
	
	public void keyPressed(int key, char code) {
		System.out.println("Key:"+code+","+key);
		System.out.println("cnt:"+targetCount+" maxcnt:"+maxTargetCount);
		if (code == 'r') {
			System.out.println("reset:"+resetCount);
			// 왜 마이너스가 안될까
			resetCount--;
			moveCount=0;
			mapInit();
		}
		else
			playerMove(key);
	}
	private void playerMove(int key) {
		//end 
		move.setPos(playerPosX, playerPosY);
		switch(key) {
		case Input.KEY_LEFT:			
			// game ending count
			targetCount+=move.leftMove(map);
			moveCount=move.getMoveCount();
			playerPosX=move.getX();
			playerPosY=move.getY();
			/*
			collision = map[playerPosY][playerPosX-1];
			if (collision == GameObjectID.EMPTY.ID || collision == GameObjectID.TARGET.ID)
				playerPosX--;
			else if (collision == GameObjectID.TELEPORTIN.ID ) {
				playerPosX=objs.get(GameObjectID.TELEPORTOUT.ID).getX();
				playerPosY=objs.get(GameObjectID.TELEPORTOUT.ID).getY();
			}
			else if (collision == GameObjectID.BALL.ID) {
				int colisionWithBall= map[playerPosY][playerPosX-2];
				if (colisionWithBall == GameObjectID.EMPTY.ID ){
					playerPosX--;
					map[playerPosY][playerPosX]=0;
					map[playerPosY][playerPosX-1]+=collision;
				}
				else if ( colisionWithBall == GameObjectID.TARGET.ID) {
					cnt++;
					playerPosX--;
					map[playerPosY][playerPosX]=0;
					map[playerPosY][playerPosX-1]+=collision;
				}
			}
			
			else if (collision == GameObjectID.FILLEDTARGET.ID) {
				int colisionWithBall= map[playerPosY][playerPosX-2];
				if (colisionWithBall == GameObjectID.EMPTY.ID ){
					cnt--;
					playerPosX--;
					map[playerPosY][playerPosX]-=GameObjectID.BALL.ID;
					map[playerPosY][playerPosX-1]+=GameObjectID.BALL.ID;
				}
				else if ( colisionWithBall == GameObjectID.TARGET.ID) {
					playerPosX--;
					map[playerPosY][playerPosX]-=GameObjectID.BALL.ID;
					map[playerPosY][playerPosX-1]+=GameObjectID.BALL.ID;
				}
			}
			*/
			break;
		case Input.KEY_RIGHT:
			targetCount+=move.rightMove(map);
			moveCount=move.getMoveCount();
			playerPosX=move.getX();
			playerPosY=move.getY();
			break;
		case Input.KEY_UP:
			targetCount+=move.upMove(map);
			moveCount=move.getMoveCount();
			playerPosX=move.getX();
			playerPosY=move.getY();
			break;
		case Input.KEY_DOWN:
			targetCount+=move.downMove(map);
			moveCount=move.getMoveCount();
			playerPosX=move.getX();
			playerPosY=move.getY();
			break;
		}
		
		//map[playerPosY][playerPosX]=saveValue;
	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.ID;
	}

}
