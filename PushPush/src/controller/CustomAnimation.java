package controller;

import org.lwjgl.Sys;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
public class CustomAnimation {
	private Image imgs;
	private int imgWidth;
	private int imgHeight;
	private int divCount;
	private long delta;
	
	private int locateWidth;
	private int locateHeight;
	
	public CustomAnimation(String fileDir,int sizeWidth, int sizeHeight,int div,long delta) {
		// TODO Auto-generated constructor stub
		try {
			imgs= new Image(fileDir);
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.imgHeight=sizeHeight;
		this.imgWidth=sizeWidth;
		this.divCount=div;
		this.delta=delta;
	}
	private long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	public void draw(int width, int height) {
		long now=System.currentTimeMillis();
		for( int i=0;i<divCount;i++){
			if (System.currentTimeMillis() - now < delta )
			imgs.getSubImage(imgWidth*i ,imgHeight, imgWidth, imgHeight).draw(locateWidth, locateHeight);
			now=System.currentTimeMillis();
		}
	}
}
