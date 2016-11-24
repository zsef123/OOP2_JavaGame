package controller;

import org.newdawn.slick.Input;

public class PlayerMove {
	private int key;
	private int targetCount;
	private int posX;
	private int posY;

	private int pos2X;
	private int pos2Y;
	
	private GameObjectID collisionID;
	private GameObjectID collisionWithBallID;
	
	private int teleportPosX;
	private int teleportPosY;
	
	private int moveCount;
	
	private AudioPlayer audio;
	// make singleton class uses holder idiom
	private PlayerMove() {
		audio=AudioPlayer.getInstance();
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
	public void setPos2(int x, int y) {
		pos2X=x;
		pos2Y=y;
	}
	public int getX() {
		return posX;
	}
	public int getY() {
		return posY;
	}
	public int get2X() {
		return pos2X;
	}
	public int get2Y() {
		return pos2Y;
	}
	
	public void setTeleportPos(int x, int y) {
		// imposible many teleport
		teleportPosX=x;
		teleportPosY=y;
	}
	private void moveToTeleport(int id) {
		audio.teleportSoundPlayAtOnce();
		if (id==GameObjectID.PLAYER1.ID) {
			posX=teleportPosX;
			posY=teleportPosY;
		}
		else if ( id == GameObjectID.PLAYER2.ID) {
			pos2X=teleportPosX;
			pos2Y=teleportPosY;
		}
	}
	
	public int leftMove(int[][] map ,int id) {
		int tmpX = 0;
		int tmpY = 0;
		if ( id== GameObjectID.PLAYER1.ID) {
			tmpX=posX;
			tmpY=posY;
		}
		else if ( id==GameObjectID.PLAYER2.ID) {
			tmpX=pos2X;
			tmpY=pos2Y;
		}
		collisionID = GameObjectID.fromInt( map[tmpY][tmpX-1] );
		switch( collisionID ) {
		// some apply on duff's device
		case EMPTY: case TARGET: case TARGET2: case TARGET3:
			tmpX--; moveCount++;
			break;
		case BALL: case FILLEDTARGET:
			collisionWithBallID =  GameObjectID.fromInt( map[tmpY][tmpX-2] );
			audio.tickPlayAtOnce();
			if ( collisionWithBallID == GameObjectID.EMPTY || collisionWithBallID == GameObjectID.TARGET) {
				audio.tickPlayAtOnce();
				tmpX--; moveCount++;
				map[tmpY][tmpX]-=GameObjectID.BALL.ID;
				map[tmpY][tmpX-1]+=GameObjectID.BALL.ID;
				if (collisionWithBallID == GameObjectID.TARGET && collisionID == GameObjectID.BALL) 
					targetCount++;	
				else if (collisionWithBallID == GameObjectID.EMPTY && collisionID == GameObjectID.FILLEDTARGET) 
					targetCount--;
			}
			break;
		case BALL2: case FILLEDTARGET2:
			collisionWithBallID =  GameObjectID.fromInt( map[tmpY][tmpX-2] );
			audio.tickPlayAtOnce();
			if ( collisionWithBallID == GameObjectID.EMPTY || collisionWithBallID == GameObjectID.TARGET2) {
				tmpX--; moveCount++;
				map[tmpY][tmpX]-=GameObjectID.BALL2.ID;
				map[tmpY][tmpX-1]+=GameObjectID.BALL2.ID;
				if (collisionWithBallID == GameObjectID.TARGET2 && collisionID == GameObjectID.BALL2) 
					targetCount++;	
				else if (collisionWithBallID == GameObjectID.EMPTY && collisionID == GameObjectID.FILLEDTARGET2) 
					targetCount--;
			}
			break;
		case BALL3: case FILLEDTARGET3:
			collisionWithBallID =  GameObjectID.fromInt( map[tmpY][tmpX-2] );
			audio.tickPlayAtOnce();
			if ( collisionWithBallID == GameObjectID.EMPTY || collisionWithBallID == GameObjectID.TARGET3) {
				tmpX--; moveCount++;
				map[tmpY][tmpX]-=GameObjectID.BALL3.ID;
				map[tmpY][tmpX-1]+=GameObjectID.BALL3.ID;
				if (collisionWithBallID == GameObjectID.TARGET3 && collisionID == GameObjectID.BALL3) 
					targetCount++;	
				else if (collisionWithBallID == GameObjectID.EMPTY && collisionID == GameObjectID.FILLEDTARGET3) 
					targetCount--;
			}
			break;
		case TELEPORTIN:
			moveToTeleport(id);
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
		if ( id==GameObjectID.PLAYER1.ID) {
			posX=tmpX;
			posY=tmpY;
		}
		else if ( id==GameObjectID.PLAYER2.ID) {
			pos2X=tmpX;
			pos2Y=tmpY;
		}
		return collisionID.ID;
	}
	public int rightMove(int[][] map, int id ) {
		int tmpX = 0;
		int tmpY = 0;
		if ( id== GameObjectID.PLAYER1.ID) {
			tmpX=posX;
			tmpY=posY;
		}
	
		else if ( id== GameObjectID.PLAYER2.ID) {
			tmpX=pos2X;
			tmpY=pos2Y;
		}
		collisionID = GameObjectID.fromInt( map[tmpY][tmpX+1] );
		switch( collisionID ) {
		case EMPTY: case TARGET: case TARGET2: case TARGET3:
			tmpX++; moveCount++;
			break;
		case BALL: case FILLEDTARGET:
			collisionWithBallID =  GameObjectID.fromInt( map[tmpY][tmpX+2] );
			audio.tickPlayAtOnce();
			if ( collisionWithBallID == GameObjectID.EMPTY || collisionWithBallID == GameObjectID.TARGET) {
				tmpX++; moveCount++;
				map[tmpY][tmpX]-=GameObjectID.BALL.ID;
				map[tmpY][tmpX+1]+=GameObjectID.BALL.ID;
				if (collisionWithBallID == GameObjectID.TARGET && collisionID == GameObjectID.BALL) 
					targetCount++;	
				else if (collisionWithBallID == GameObjectID.EMPTY && collisionID == GameObjectID.FILLEDTARGET) 
					targetCount--;
			}
			break;
		case BALL2: case FILLEDTARGET2:
			audio.tickPlayAtOnce();
			collisionWithBallID =  GameObjectID.fromInt( map[tmpY][tmpX+2] );
			if ( collisionWithBallID == GameObjectID.EMPTY || collisionWithBallID == GameObjectID.TARGET2) {
				tmpX++; moveCount++;
				map[tmpY][tmpX]-=GameObjectID.BALL2.ID;
				map[tmpY][tmpX+1]+=GameObjectID.BALL2.ID;
				if (collisionWithBallID == GameObjectID.TARGET2 && collisionID == GameObjectID.BALL2) 
					targetCount++;	
				else if (collisionWithBallID == GameObjectID.EMPTY && collisionID == GameObjectID.FILLEDTARGET2) 
					targetCount--;
			}
			break;
		case BALL3: case FILLEDTARGET3:
			audio.tickPlayAtOnce();
			collisionWithBallID =  GameObjectID.fromInt( map[tmpY][tmpX+2] );
			if ( collisionWithBallID == GameObjectID.EMPTY || collisionWithBallID == GameObjectID.TARGET3) {
				tmpX++; moveCount++;
				map[tmpY][tmpX]-=GameObjectID.BALL3.ID;
				map[tmpY][tmpX+1]+=GameObjectID.BALL3.ID;
				if (collisionWithBallID == GameObjectID.TARGET3 && collisionID == GameObjectID.BALL3) 
					targetCount++;	
				else if (collisionWithBallID == GameObjectID.EMPTY && collisionID == GameObjectID.FILLEDTARGET3) 
					targetCount--;
			}
			break;
		case TELEPORTIN:
			moveToTeleport(id);
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
		if ( id==GameObjectID.PLAYER1.ID) {
			posX=tmpX;
			posY=tmpY;
		}
		else if ( id==GameObjectID.PLAYER2.ID) {
			pos2X=tmpX;
			pos2Y=tmpY;
		}
		return collisionID.ID;
	}
	public int upMove(int[][] map, int id ) {
		int tmpX = 0;
		int tmpY = 0;
		if ( id== GameObjectID.PLAYER1.ID) {
			tmpX=posX;
			tmpY=posY;
		}
	
		else if ( id== GameObjectID.PLAYER2.ID) {
			tmpX=pos2X;
			tmpY=pos2Y;
		}
		collisionID = GameObjectID.fromInt( map[tmpY-1][tmpX] );
		switch( collisionID ) {
		case EMPTY: case TARGET: case TARGET2: case TARGET3:
			tmpY--; moveCount++;
			break;
		case BALL: case FILLEDTARGET:
			audio.tickPlayAtOnce();
			collisionWithBallID =  GameObjectID.fromInt( map[tmpY-2][tmpX] );
			if ( collisionWithBallID == GameObjectID.EMPTY || collisionWithBallID == GameObjectID.TARGET) {
				tmpY--; moveCount++;
				map[tmpY][tmpX]-=GameObjectID.BALL.ID;
				map[tmpY-1][tmpX]+=GameObjectID.BALL.ID;
				if (collisionWithBallID == GameObjectID.TARGET && collisionID == GameObjectID.BALL) 
					targetCount++;	
				else if (collisionWithBallID == GameObjectID.EMPTY && collisionID == GameObjectID.FILLEDTARGET) 
					targetCount--;
			}
			break;
		case BALL2: case FILLEDTARGET2:
			audio.tickPlayAtOnce();
			collisionWithBallID =  GameObjectID.fromInt( map[tmpY-2][tmpX] );
			if ( collisionWithBallID == GameObjectID.EMPTY || collisionWithBallID == GameObjectID.TARGET2) {
				tmpY--; moveCount++;
				map[tmpY][tmpX]-=GameObjectID.BALL2.ID;
				map[tmpY-1][tmpX]+=GameObjectID.BALL2.ID;
				if (collisionWithBallID == GameObjectID.TARGET2 && collisionID == GameObjectID.BALL2) 
					targetCount++;	
				else if (collisionWithBallID == GameObjectID.EMPTY && collisionID == GameObjectID.FILLEDTARGET2) 
					targetCount--;
			}
			break;
		case BALL3: case FILLEDTARGET3:
			audio.tickPlayAtOnce();
			collisionWithBallID =  GameObjectID.fromInt( map[tmpY-2][tmpX] );
			if ( collisionWithBallID == GameObjectID.EMPTY || collisionWithBallID == GameObjectID.TARGET3) {
				tmpY--; moveCount++;
				map[tmpY][tmpX]-=GameObjectID.BALL3.ID;
				map[tmpY-1][tmpX]+=GameObjectID.BALL3.ID;
				if (collisionWithBallID == GameObjectID.TARGET3 && collisionID == GameObjectID.BALL3) 
					targetCount++;	
				else if (collisionWithBallID == GameObjectID.EMPTY && collisionID == GameObjectID.FILLEDTARGET3) 
					targetCount--;
			}
			break;
		case TELEPORTIN:
			moveToTeleport(id);
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
		if ( id==GameObjectID.PLAYER1.ID) {
			posX=tmpX;
			posY=tmpY;
		}
		else if ( id==GameObjectID.PLAYER2.ID) {
			pos2X=tmpX;
			pos2Y=tmpY;
		}
		return collisionID.ID;
	}
	public int downMove(int[][] map,int id ) {
		int tmpX = 0;
		int tmpY = 0;
		if ( id== GameObjectID.PLAYER1.ID) {
			tmpX=posX;
			tmpY=posY;
		}
	
		else if ( id== GameObjectID.PLAYER2.ID) {
			tmpX=pos2X;
			tmpY=pos2Y;
		}
		collisionID = GameObjectID.fromInt( map[tmpY+1][tmpX] );
		switch( collisionID ) {
		case EMPTY: case TARGET: case TARGET2: case TARGET3:
			tmpY++; moveCount++;
			break;
		case BALL: case FILLEDTARGET:
			audio.tickPlayAtOnce();
			collisionWithBallID =  GameObjectID.fromInt( map[tmpY+2][tmpX] );
			if ( collisionWithBallID == GameObjectID.EMPTY || collisionWithBallID == GameObjectID.TARGET) {
				tmpY++; moveCount++;
				map[tmpY][tmpX]-=GameObjectID.BALL.ID;
				map[tmpY+1][tmpX]+=GameObjectID.BALL.ID;
				if (collisionWithBallID == GameObjectID.TARGET && collisionID == GameObjectID.BALL) 
					targetCount++;	
				else if (collisionWithBallID == GameObjectID.EMPTY && collisionID == GameObjectID.FILLEDTARGET) 
					targetCount--;
			}
			break;
		case BALL2: case FILLEDTARGET2:
			audio.tickPlayAtOnce();
			collisionWithBallID =  GameObjectID.fromInt( map[tmpY+2][tmpX] );
			if ( collisionWithBallID == GameObjectID.EMPTY || collisionWithBallID == GameObjectID.TARGET2) {
				tmpY++; moveCount++;
				map[tmpY][tmpX]-=GameObjectID.BALL2.ID;
				map[tmpY+1][tmpX]+=GameObjectID.BALL2.ID;
				if (collisionWithBallID == GameObjectID.TARGET2 && collisionID == GameObjectID.BALL2) 
					targetCount++;	
				else if (collisionWithBallID == GameObjectID.EMPTY && collisionID == GameObjectID.FILLEDTARGET2) 
					targetCount--;
			}
			break;
		case BALL3: case FILLEDTARGET3:
			audio.tickPlayAtOnce();
			collisionWithBallID =  GameObjectID.fromInt( map[tmpY+2][tmpX] );
			if ( collisionWithBallID == GameObjectID.EMPTY || collisionWithBallID == GameObjectID.TARGET3) {
				tmpY++; moveCount++;
				map[tmpY][tmpX]-=GameObjectID.BALL3.ID;
				map[tmpY+1][tmpX]+=GameObjectID.BALL3.ID;
				if (collisionWithBallID == GameObjectID.TARGET3 && collisionID == GameObjectID.BALL3) 
					targetCount++;	
				else if (collisionWithBallID == GameObjectID.EMPTY && collisionID == GameObjectID.FILLEDTARGET3) 
					targetCount--;
			}
			break;
		case TELEPORTIN:
			moveToTeleport(id);
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
		if ( id==GameObjectID.PLAYER1.ID) {
			posX=tmpX;
			posY=tmpY;
		}
		else if ( id==GameObjectID.PLAYER2.ID) {
			pos2X=tmpX;
			pos2Y=tmpY;
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