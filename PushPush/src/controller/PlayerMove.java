package controller;

import org.newdawn.slick.Input;

import gameStates.Stage;
import models.GameObject;

public class PlayerMove {
	private int key;
	
	private int posX;
	private int posY;
	
	private GameObjectID collisionID;
	private GameObjectID collisionWithBallID;
	
	private int teleportPosX;
	private int teleportPosY;
	
	private int moveCount;
	
	// make singleton class uses holder idiom
	private PlayerMove() {
	}
	private static class Singleton {
		private static final PlayerMove instance = new PlayerMove();
	} 
	public static PlayerMove getInstance() {
		return Singleton.instance;
	}
	
	public int setZeroMoveCount() {
		moveCount=0;
		return 0;
	}
	public int getMoveCount() {
		return moveCount;
	}
	
	public void setPos(int x, int y) {
		posX=x;
		posY=y;
	}
	public int getX() {
		return posX;
	}
	public int getY() {
		return posY;
	}
	public void setTeleportPos(int x, int y) {
		// imposible many teleport
		teleportPosX=x;
		teleportPosY=y;
	}
	private void moveToTeleport() {
		posX=teleportPosX;
		posY=teleportPosY;
	}
	
	public int leftMove(int[][] map ) {
		collisionID = GameObjectID.fromInt( map[posY][posX-1] );
		int targetCount=0;
		switch( collisionID ) {
		// some apply on duff's device
		case EMPTY: case TARGET:
			posX--; moveCount++;
			break;
		case BALL: case FILLEDTARGET:
			collisionWithBallID =  GameObjectID.fromInt( map[posY][posX-2] );
			if ( collisionWithBallID == GameObjectID.EMPTY || collisionWithBallID == GameObjectID.TARGET) {
				posX--; moveCount++;
				map[posY][posX]-=GameObjectID.BALL.ID;
				// 공을 밀고 값을 안바꾸잖아.
				map[posY][posX-1]+=GameObjectID.BALL.ID;
				if (collisionWithBallID == GameObjectID.TARGET && collisionID == GameObjectID.BALL) 
					targetCount++;	
				else if (collisionWithBallID == GameObjectID.EMPTY && collisionID == GameObjectID.FILLEDTARGET) 
					targetCount--;
			}
			break;
		case TELEPORTIN:
			moveToTeleport();
			break;
		case TELEPORTOUT:
			// don't do it
			break;
		case WALL:
			// don't do it
			break;
		case UPLADDER:
			
			break;
		default:
			break;
			
		}
		return targetCount;
	}
	public int rightMove(int[][] map ) {
		collisionID = GameObjectID.fromInt( map[posY][posX+1] );
		int targetCount=0;
		switch( collisionID ) {
		case EMPTY: case TARGET:
			posX++; moveCount++;
			break;
		case BALL: case FILLEDTARGET:
			collisionWithBallID =  GameObjectID.fromInt( map[posY][posX+2] );
			if ( collisionWithBallID == GameObjectID.EMPTY || collisionWithBallID == GameObjectID.TARGET) {
				posX++; moveCount++;
				map[posY][posX]-=GameObjectID.BALL.ID;
				// 공을 밀고 값을 안바꾸잖아.
				map[posY][posX+1]+=GameObjectID.BALL.ID;
				if (collisionWithBallID == GameObjectID.TARGET && collisionID == GameObjectID.BALL) 
					targetCount++;	
				else if (collisionWithBallID == GameObjectID.EMPTY && collisionID == GameObjectID.FILLEDTARGET) 
					targetCount--;
			}
			break;
		case TELEPORTIN:
			moveToTeleport();
			break;
		case TELEPORTOUT:
			// do nothing
			break;
		case WALL:
			// do nothing
			break;
		default:
			break;
			
		}
		return targetCount;
	}
	public int upMove(int[][] map ) {
		collisionID = GameObjectID.fromInt( map[posY-1][posX] );
		int targetCount=0;
		switch( collisionID ) {
		case EMPTY: case TARGET:
			posY--; moveCount++;
			break;
		case BALL: case FILLEDTARGET:
			collisionWithBallID =  GameObjectID.fromInt( map[posY-2][posX] );
			if ( collisionWithBallID == GameObjectID.EMPTY || collisionWithBallID == GameObjectID.TARGET) {
				posY--; moveCount++;
				map[posY][posX]-=GameObjectID.BALL.ID;
				// 공을 밀고 값을 안바꾸잖아.
				map[posY-1][posX]+=GameObjectID.BALL.ID;
				if (collisionWithBallID == GameObjectID.TARGET && collisionID == GameObjectID.BALL) 
					targetCount++;	
				else if (collisionWithBallID == GameObjectID.EMPTY && collisionID == GameObjectID.FILLEDTARGET) 
					targetCount--;
			}
			break;
		case TELEPORTIN:
			moveToTeleport();
			break;
		case TELEPORTOUT:
			// don't do it
			break;
		case WALL:
			// don't do it
			break;
		default:
			break;
			
		}
		return targetCount;
	}
	public int downMove(int[][] map ) {
		collisionID = GameObjectID.fromInt( map[posY+1][posX] );
		int targetCount=0;
		switch( collisionID ) {
		case EMPTY: case TARGET:
			posY++; moveCount++;
			break;
		case BALL: case FILLEDTARGET:
			collisionWithBallID =  GameObjectID.fromInt( map[posY+2][posX] );
			if ( collisionWithBallID == GameObjectID.EMPTY || collisionWithBallID == GameObjectID.TARGET) {
				posY++; moveCount++;
				map[posY][posX]-=GameObjectID.BALL.ID;
				// 공을 밀고 값을 안바꾸잖아.
				map[posY+1][posX]+=GameObjectID.BALL.ID;
				if (collisionWithBallID == GameObjectID.TARGET && collisionID == GameObjectID.BALL) 
					targetCount++;	
				else if (collisionWithBallID == GameObjectID.EMPTY && collisionID == GameObjectID.FILLEDTARGET) 
					targetCount--;
			}
			break;
		case TELEPORTIN:
			moveToTeleport();
			break;
		case TELEPORTOUT:
			// don't do it
			break;
		case WALL:
			// don't do it
			break;
		default:
			break;
			
		}
		return targetCount;
	}
	
	public int keySwitch(int k) {
		this.key=k;
		switch(key) {
		case Input.KEY_LEFT:
			break;
		case Input.KEY_RIGHT:
			break;
		case Input.KEY_DOWN:
			break;
		case Input.KEY_UP:
			break;
			
		}
		return 0;
	}
	
}