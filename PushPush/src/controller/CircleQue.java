package controller;

import java.util.Vector;

public class CircleQue {
	private int front;
    private int rear;
    
	public int maxsize;
	private int[][] map;
	public CircleQue(int size) {
		// TODO Auto-generated constructor stub
		front=0;
		rear=0;
		// char 기준 움직이는건 3칸 player col colwithball 이런식으로.
		this.maxsize=size;
		map=new int[maxsize][3];
	}
	public boolean isEmpty() {
        return (front==rear);
    }
	public boolean isFull() {
        return ((rear+1)%this.maxsize==front);
    }
    public void enQueue(int[] map) {
        rear = (rear+1)%(maxsize);
        this.map[rear] = map; 
    }
    public int[] deQueue() {
        front = (front+1)%maxsize;
        return map[front];
    	
    }
}
