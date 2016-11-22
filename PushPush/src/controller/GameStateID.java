package controller;

public enum GameStateID {
	TITLE(0), MAINFRAME(1), ENDING(100), PAUSE(99);
	public int ID;

	GameStateID(int a) {
		this.ID = a;
	}

	public int getValue() {
		return ID;
	}
}
