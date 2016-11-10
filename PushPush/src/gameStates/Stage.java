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
public abstract class Stage extends BasicGameState {
	StateBasedGame game;
	GameContainer gc;
	protected int ID;
	protected int stageIndex;
	protected Image bgImage;
	protected HashMap<Integer,GameObject> objs;
	protected String fileDir="C:\\javaProject\\JavaModels\\Stages\\";
	// 그려줄 맵
	protected int time;
	protected int cnt;
	protected int maxCnt;
	protected int playerPosX;
	protected int playerPosY;
	private int saveMapValue;
	public int[][] map;
	public final int mapWidth=20;
	public final int mapHeight=15;
	public Stage(int id) {
		// TODO Auto-generated constructor stub
		this.ID=id;
		cnt=0;
	}

	@Override
	abstract public void init(GameContainer gc, StateBasedGame sbg) throws SlickException;
	public void mapInit() {
		// TODO Auto-generated method stub
		cnt=0;
		maxCnt=0;
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
					if (map[i][j] == GameObjectID.TARGET.ID) {
						maxCnt++;
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
		objs.get(1).getImage().draw(playerPosX*20+10, playerPosY*20+5);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		// TODO Auto-generated method stub
		
	}
	public void keyPressed(int key, char code) {
		System.out.println("Key:"+code+","+key);
		System.out.println("cnt:"+cnt+" maxcnt:"+maxCnt);
		if (code == 'r') {
			System.out.println("reset");
			mapInit();
		}
		else
			playerMove(key);
	}
	private void playerMove(int key) {
		//map[playerPosY][playerPosX]=0;
		int collision=0;
		int saveValue = map[playerPosY][playerPosX];
		switch(key) {
		case Input.KEY_LEFT:
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
			break;
		case Input.KEY_RIGHT:
			collision = map[playerPosY][playerPosX+1];
			if (collision == GameObjectID.EMPTY.ID || collision == GameObjectID.TARGET.ID)
				playerPosX++;
			else if (collision == GameObjectID.TELEPORTIN.ID ) {
				playerPosX=objs.get(GameObjectID.TELEPORTOUT.ID).getX();
				playerPosY=objs.get(GameObjectID.TELEPORTOUT.ID).getY();
			}
			else if (collision == GameObjectID.BALL.ID) {
				int colisionWithBall= map[playerPosY][playerPosX+2];
				if (colisionWithBall == GameObjectID.EMPTY.ID ){
					playerPosX++;
					map[playerPosY][playerPosX]=0;
					map[playerPosY][playerPosX+1]+=collision;
				}
				else if ( colisionWithBall == GameObjectID.TARGET.ID) {
					cnt++;
					playerPosX++;
					map[playerPosY][playerPosX]=0;
					map[playerPosY][playerPosX+1]+=collision;
				}
			}
			else if (collision == GameObjectID.FILLEDTARGET.ID) {
				int colisionWithBall= map[playerPosY][playerPosX+2];
				if (colisionWithBall == GameObjectID.EMPTY.ID ){
					cnt--;
					playerPosX++;
					map[playerPosY][playerPosX]-=GameObjectID.BALL.ID;
					map[playerPosY][playerPosX+1]+=GameObjectID.BALL.ID;
				}
				else if ( colisionWithBall == GameObjectID.TARGET.ID) {
					playerPosX++;
					map[playerPosY][playerPosX]-=GameObjectID.BALL.ID;
					map[playerPosY][playerPosX+1]+=GameObjectID.BALL.ID;
				}
			}
			break;
		case Input.KEY_UP:
			collision = map[playerPosY-1][playerPosX];
			if (collision == GameObjectID.EMPTY.ID || collision == GameObjectID.TARGET.ID)
				playerPosY--;
			else if (collision == GameObjectID.TELEPORTIN.ID ) {
				playerPosX=objs.get(GameObjectID.TELEPORTOUT.ID).getX();
				playerPosY=objs.get(GameObjectID.TELEPORTOUT.ID).getY();
			}
			else if (collision == GameObjectID.BALL.ID) {
				int colisionWithBall= map[playerPosY-2][playerPosX];
				if (colisionWithBall == GameObjectID.EMPTY.ID ){
					playerPosY--;
					map[playerPosY][playerPosX]=0;
					map[playerPosY-1][playerPosX]+=collision;
				}
				else if ( colisionWithBall == GameObjectID.TARGET.ID) {
					cnt++;
					playerPosY--;
					map[playerPosY][playerPosX]=0;
					map[playerPosY-1][playerPosX]+=collision;
				}
			}
			else if (collision == GameObjectID.FILLEDTARGET.ID) {
				int colisionWithBall= map[playerPosY-2][playerPosX];
				if (colisionWithBall == GameObjectID.EMPTY.ID ){
					cnt--;
					playerPosY--;
					map[playerPosY][playerPosX]-=GameObjectID.BALL.ID;
					map[playerPosY-1][playerPosX]+=GameObjectID.BALL.ID;
				}
				else if ( colisionWithBall == GameObjectID.TARGET.ID) {
					playerPosY--;
					map[playerPosY][playerPosX]-=GameObjectID.BALL.ID;
					map[playerPosY-1][playerPosX]+=GameObjectID.BALL.ID;
				}
			}
			break;
		case Input.KEY_DOWN:
			collision = map[playerPosY+1][playerPosX];
			if (collision == GameObjectID.EMPTY.ID || collision == GameObjectID.TARGET.ID)
				playerPosY++;
			else if (collision == GameObjectID.TELEPORTIN.ID ) {
				playerPosX=objs.get(GameObjectID.TELEPORTOUT.ID).getX();
				playerPosY=objs.get(GameObjectID.TELEPORTOUT.ID).getY();
			}
			else if (collision == GameObjectID.BALL.ID) {
				int colisionWithBall= map[playerPosY+2][playerPosX];
				if (colisionWithBall == GameObjectID.EMPTY.ID ){
					playerPosY++;
					map[playerPosY][playerPosX]=0;
					map[playerPosY+1][playerPosX]+=collision;
				}
				else if ( colisionWithBall == GameObjectID.TARGET.ID) {
					cnt++;
					playerPosY++;
					map[playerPosY][playerPosX]=0;
					map[playerPosY+1][playerPosX]+=collision;
				}
			}
			else if (collision == GameObjectID.FILLEDTARGET.ID) {
				int colisionWithBall= map[playerPosY+2][playerPosX];
				if (colisionWithBall == GameObjectID.EMPTY.ID ){
					cnt--;
					playerPosY++;
					map[playerPosY][playerPosX]-=GameObjectID.BALL.ID;
					map[playerPosY+1][playerPosX]+=GameObjectID.BALL.ID;
				}
				else if ( colisionWithBall == GameObjectID.TARGET.ID) {
					playerPosY++;
					map[playerPosY][playerPosX]-=GameObjectID.BALL.ID;
					map[playerPosY+1][playerPosX]+=GameObjectID.BALL.ID;
				}
			}
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
