package controller;

public class Undo {
	private int posX;
	private int posY;
	private int targetCount;
	private int[][] map;
	public Undo() {
		
	}
	public Undo(int x, int y, int targetcount,int[][] map) {
		// TODO Auto-generated constructor stub
		setUndo(x,y,targetcount,map);
	}
	
	public void setUndo(int x, int y, int targetcount,int[][] m) {
		this.posX=x;
		this.posY=y;
		this.targetCount=targetcount;
		//this.map=m;
		map=new int[20][15];
		for(int i=0; i<20; i++)
			  for(int j=0; j<15; j++)
			    map[i][j]=m[i][j];
	}
	
	public int getX() {
		return posX;
	}
	public int getY() {
		return posY;
	}
	public int getTarget(){
		return targetCount;
	}
	public int[][] getMap() {
		return map;
	}
	

}
