package controller;

import org.newdawn.slick.Input;

import gameStates.Stage;
import models.GameObject;

public class PlayerMove {
	private int key;
	private int targetCount;
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
	private static class SingletonMove {
		private static final PlayerMove instance = new PlayerMove();
	} 
	public static PlayerMove getInstance() {
		return SingletonMove.instance;
	}
	
	public int setZeroMoveCount() {
		moveCount=0;
		return 0;
	}
	public int setZeroTargetCount() {
		targetCount=0;
		return 0;
	}
	public int getMoveCount() {
		return moveCount;
	}
	public void setTargetCount(int count) {
		targetCount=count;
	}
	public int getTargetCount() {
		return targetCount;
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
		switch( collisionID ) {
		// some apply on duff's device
		case EMPTY: case TARGET: case TARGET2: case TARGET3:
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
		case BALL2: case FILLEDTARGET2:
			collisionWithBallID =  GameObjectID.fromInt( map[posY][posX-2] );
			if ( collisionWithBallID == GameObjectID.EMPTY || collisionWithBallID == GameObjectID.TARGET2) {
				posX--; moveCount++;
				map[posY][posX]-=GameObjectID.BALL2.ID;
				// 공을 밀고 값을 안바꾸잖아.
				map[posY][posX-1]+=GameObjectID.BALL2.ID;
				if (collisionWithBallID == GameObjectID.TARGET2 && collisionID == GameObjectID.BALL2) 
					targetCount++;	
				else if (collisionWithBallID == GameObjectID.EMPTY && collisionID == GameObjectID.FILLEDTARGET2) 
					targetCount--;
			}
			break;
		case BALL3: case FILLEDTARGET3:
			collisionWithBallID =  GameObjectID.fromInt( map[posY][posX-2] );
			if ( collisionWithBallID == GameObjectID.EMPTY || collisionWithBallID == GameObjectID.TARGET3) {
				posX--; moveCount++;
				map[posY][posX]-=GameObjectID.BALL3.ID;
				// 공을 밀고 값을 안바꾸잖아.
				map[posY][posX-1]+=GameObjectID.BALL3.ID;
				if (collisionWithBallID == GameObjectID.TARGET3 && collisionID == GameObjectID.BALL3) 
					targetCount++;	
				else if (collisionWithBallID == GameObjectID.EMPTY && collisionID == GameObjectID.FILLEDTARGET3) 
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
		return collisionID.ID;
	}
	public int rightMove(int[][] map ) {
		collisionID = GameObjectID.fromInt( map[posY][posX+1] );
		switch( collisionID ) {
		case EMPTY: case TARGET: case TARGET2: case TARGET3:
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
		case BALL2: case FILLEDTARGET2:
			collisionWithBallID =  GameObjectID.fromInt( map[posY][posX+2] );
			if ( collisionWithBallID == GameObjectID.EMPTY || collisionWithBallID == GameObjectID.TARGET2) {
				posX++; moveCount++;
				map[posY][posX]-=GameObjectID.BALL2.ID;
				// 공을 밀고 값을 안바꾸잖아.
				map[posY][posX+1]+=GameObjectID.BALL2.ID;
				if (collisionWithBallID == GameObjectID.TARGET2 && collisionID == GameObjectID.BALL2) 
					targetCount++;	
				else if (collisionWithBallID == GameObjectID.EMPTY && collisionID == GameObjectID.FILLEDTARGET2) 
					targetCount--;
			}
			break;
		case BALL3: case FILLEDTARGET3:
			collisionWithBallID =  GameObjectID.fromInt( map[posY][posX+2] );
			if ( collisionWithBallID == GameObjectID.EMPTY || collisionWithBallID == GameObjectID.TARGET3) {
				posX++; moveCount++;
				map[posY][posX]-=GameObjectID.BALL3.ID;
				// 공을 밀고 값을 안바꾸잖아.
				map[posY][posX+1]+=GameObjectID.BALL3.ID;
				if (collisionWithBallID == GameObjectID.TARGET3 && collisionID == GameObjectID.BALL3) 
					targetCount++;	
				else if (collisionWithBallID == GameObjectID.EMPTY && collisionID == GameObjectID.FILLEDTARGET3) 
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
		return collisionID.ID;
	}
	public int upMove(int[][] map ) {
		collisionID = GameObjectID.fromInt( map[posY-1][posX] );
		switch( collisionID ) {
		case EMPTY: case TARGET: case TARGET2: case TARGET3:
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
		case BALL2: case FILLEDTARGET2:
			collisionWithBallID =  GameObjectID.fromInt( map[posY-2][posX] );
			if ( collisionWithBallID == GameObjectID.EMPTY || collisionWithBallID == GameObjectID.TARGET2) {
				posY--; moveCount++;
				map[posY][posX]-=GameObjectID.BALL2.ID;
				// 공을 밀고 값을 안바꾸잖아.
				map[posY-1][posY]+=GameObjectID.BALL2.ID;
				if (collisionWithBallID == GameObjectID.TARGET2 && collisionID == GameObjectID.BALL2) 
					targetCount++;	
				else if (collisionWithBallID == GameObjectID.EMPTY && collisionID == GameObjectID.FILLEDTARGET2) 
					targetCount--;
			}
			break;
		case BALL3: case FILLEDTARGET3:
			collisionWithBallID =  GameObjectID.fromInt( map[posY-2][posX] );
			if ( collisionWithBallID == GameObjectID.EMPTY || collisionWithBallID == GameObjectID.TARGET3) {
				posY--; moveCount++;
				map[posY][posX]-=GameObjectID.BALL3.ID;
				// 공을 밀고 값을 안바꾸잖아.
				map[posY-1][posY]+=GameObjectID.BALL3.ID;
				if (collisionWithBallID == GameObjectID.TARGET3 && collisionID == GameObjectID.BALL3) 
					targetCount++;	
				else if (collisionWithBallID == GameObjectID.EMPTY && collisionID == GameObjectID.FILLEDTARGET3) 
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
		return collisionID.ID;
	}
	public int downMove(int[][] map ) {
		collisionID = GameObjectID.fromInt( map[posY+1][posX] );
		switch( collisionID ) {
		case EMPTY: case TARGET: case TARGET2: case TARGET3:
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
		case BALL2: case FILLEDTARGET2:
			collisionWithBallID =  GameObjectID.fromInt( map[posY+2][posX] );
			if ( collisionWithBallID == GameObjectID.EMPTY || collisionWithBallID == GameObjectID.TARGET2) {
				posY++; moveCount++;
				map[posY][posX]-=GameObjectID.BALL2.ID;
				// 공을 밀고 값을 안바꾸잖아.
				map[posY+1][posX]+=GameObjectID.BALL2.ID;
				if (collisionWithBallID == GameObjectID.TARGET2 && collisionID == GameObjectID.BALL2) 
					targetCount++;	
				else if (collisionWithBallID == GameObjectID.EMPTY && collisionID == GameObjectID.FILLEDTARGET2) 
					targetCount--;
			}
			break;
		case BALL3: case FILLEDTARGET3:
			collisionWithBallID =  GameObjectID.fromInt( map[posY+2][posX] );
			if ( collisionWithBallID == GameObjectID.EMPTY || collisionWithBallID == GameObjectID.TARGET3) {
				posY++; moveCount++;
				map[posY][posX]-=GameObjectID.BALL3.ID;
				// 공을 밀고 값을 안바꾸잖아.
				map[posY+1][posX]+=GameObjectID.BALL3.ID;
				if (collisionWithBallID == GameObjectID.TARGET3 && collisionID == GameObjectID.BALL3) 
					targetCount++;	
				else if (collisionWithBallID == GameObjectID.EMPTY && collisionID == GameObjectID.FILLEDTARGET3) 
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
		return collisionID.ID;
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