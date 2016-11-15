package controller;

public enum GameObjectID {
	EMPTY(0), WALL(9), PLAYER1(1)
	, BALL(2), BALL2(12)
	, TARGET(3), FILLEDTARGET(5), TARGET2(4), FILLEDTARGET2(16)
	, TELEPORTIN(25), TELEPORTOUT(26)
	, UPLADDER(51);
	public int ID;

	GameObjectID(int a) {
		this.ID = a;
	}

	public int getID() {
		return ID;
	}
	public static GameObjectID fromInt(int value) {
		for (GameObjectID g: GameObjectID.values()) {
			if ( g.ID == value ) {
				return g;
			}
		}
		// indirected nullpointerexception throws
		return null;
	}
}
