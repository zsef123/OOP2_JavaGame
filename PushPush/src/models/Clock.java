package models;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Clock {
	private Image[] clockImages;
	private Image[] flipClock;
	private SpriteSheet[] clockSprites;
	private Animation[] flipClockSprite;
	private int[] time;
	private int[] beforeTime;
	private Clock()   {
		// TODO Auto-generated constructor stub
		flipClock = new Image[4];
		clockImages= new Image[10];
		clockSprites= new SpriteSheet[10];
		flipClockSprite=new Animation[4];
		for (int i=0;i<10;i++) {
			try {
				clockSprites[i]=new SpriteSheet("C:\\javaProject\\JavaModels\\clocks\\flip"+Integer.toString(i)+".png", 50, 50);
				
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i=0; i<10;i++ ) {
			String imageDir = "C:\\javaProject\\JavaModels\\clocks\\clock"+Integer.toString(i)+".png";
			
			// not throws exception for use Singleton
			try {
				clockImages[i]=new Image(imageDir);
			} catch (SlickException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i=0;i<4;i++) {
			flipClock[i]=clockImages[0];
			flipClockSprite[i]=new Animation(clockSprites[0],20);
			flipClockSprite[i].setLooping(false);
		}
		beforeTime=new int[4];
		time=new int[4];
		for (int i=0;i<4;i++ ){
			beforeTime[i]=0;
			time[i]=0;
		}
	} 
	private static class SingletonClock {
		private static final Clock instance = new Clock();
	} 
	public static Clock getInstance() {
		return SingletonClock.instance;
	}
	public void setTime(int timer) {

		for(int i=0;i<4;i++ ) {
			beforeTime[i]=time[i];
		}
		int minute=timer/60;
		// overflow
		if ( minute > 99 ) 
			minute-=99;
		int second=timer%60;
		this.time[0]=minute/10;
		this.time[1]=minute%10;
		this.time[2]=second/10;
		this.time[3]=second%10;
		
		for (int i=0;i<4;i++ ) {
			int timeQuantum=time[i];
			flipClock[i]=clockImages[timeQuantum];
			if (beforeTime[i] != time[i]) {
				flipClockSprite[i]=new Animation(clockSprites[timeQuantum], 20);
				flipClockSprite[i].setLooping(false);
			}
		}
	}
	public Image[] getFlip() {
		return flipClock;
	}
	public Animation[] getSprite() {
		return flipClockSprite;
	}
	public int[] getTime() {
		return time;
	}
	public int[] getBeforeTime() {
		return beforeTime;
	}
}
