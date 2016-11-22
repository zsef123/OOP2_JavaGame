package controller;

public enum Direction {
	DOWN(0), UP(1), LEFT(2), RIGHT(3);
	public int ID;

	Direction(int a) {
		this.ID = a;
	}

	public int getID() {
		return ID;
	}
}
