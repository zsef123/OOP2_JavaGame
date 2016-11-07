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
	protected int ID;
	protected int stageIndex;
	protected Image bgImage;
	protected HashMap<Integer,GameObject> objs;
	// 그려줄 맵
	
	protected Image a;
	protected Image p;
	protected int playerPosX;
	protected int playerPosY;
	public int[][] map;
	public final int mapWidth=20;
	public final int mapHeight=15;
	protected int cnt;
	public Stage(int id) {
		// TODO Auto-generated constructor stub
		this.ID=id;
	}

	@Override
	abstract public void init(GameContainer gc, StateBasedGame sbg) throws SlickException;
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		//해상도로 바꾼다
		bgImage.draw(0,0,640,480);
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
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int arg2) throws SlickException {
		// TODO Auto-generated method stub
		if ( cnt==3) {
			game.enterState(GameStateID.TITLE.ID);
		}
	}
	public void keyPressed(int key, char code) {
		System.out.println("Key:"+code+","+key);
		playerMove(key);
	}
	private void playerMove(int key) {
		map[playerPosY][playerPosX]=0;
		int collision;
		switch(key) {
		case Input.KEY_LEFT:
			collision = map[playerPosY][playerPosX-1];
			if (collision == GameObjectID.EMPTY.ID)
				playerPosX--;
			else if (collision == GameObjectID.BALL.ID) {
				int colisionWithBall= map[playerPosY][playerPosX-2];
				if (colisionWithBall == GameObjectID.EMPTY.ID ){
					playerPosX--;
					map[playerPosY][playerPosX-1]+=collision;
				}
				else if ( colisionWithBall == GameObjectID.TARGET.ID) {
					cnt++;
					playerPosX--;
					map[playerPosY][playerPosX-1]+=collision;
				}
			}
			break;
		case Input.KEY_RIGHT:
			collision = map[playerPosY][playerPosX+1];
			if (collision == GameObjectID.EMPTY.ID)
				playerPosX++;
			else if (collision == GameObjectID.BALL.ID) {
				int colisionWithBall= map[playerPosY][playerPosX+2];
				if (colisionWithBall == GameObjectID.EMPTY.ID ){
					playerPosX++;
					map[playerPosY][playerPosX+1]+=collision;
				}
				else if ( colisionWithBall == GameObjectID.TARGET.ID) {
					cnt++;
					playerPosX++;
					map[playerPosY][playerPosX+1]+=collision;
				}
			}
			break;
		case Input.KEY_UP:
			collision = map[playerPosY-1][playerPosX];
			if (collision == GameObjectID.EMPTY.ID)
				playerPosY--;
			else if (collision == GameObjectID.BALL.ID) {
				int colisionWithBall= map[playerPosY-2][playerPosX];
				if (colisionWithBall == GameObjectID.EMPTY.ID ){
					playerPosY--;
					map[playerPosY-1][playerPosX]+=collision;
				}
				else if ( colisionWithBall == GameObjectID.TARGET.ID) {
					cnt++;
					playerPosY--;
					map[playerPosY-1][playerPosX]+=collision;
				}
			}
			break;
		case Input.KEY_DOWN:
			collision = map[playerPosY+1][playerPosX];
			if (collision == GameObjectID.EMPTY.ID)
				playerPosY++;
			else if (collision == GameObjectID.BALL.ID) {
				int colisionWithBall= map[playerPosY+2][playerPosX];
				if (colisionWithBall == GameObjectID.EMPTY.ID ){
					playerPosY++;
					map[playerPosY+1][playerPosX]+=collision;
				}
				else if ( colisionWithBall == GameObjectID.TARGET.ID) {
					cnt++;
					playerPosY++;
					map[playerPosY+1][playerPosX]+=collision;
				}
			}
			break;
		}
		map[playerPosY][playerPosX]=1;
	}
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return this.ID;
	}

}
