package controller;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {
	private Music bgm;
	
	private Sound tick;
	private Sound teleport;
	
	private Music endingBgm;
	private float bgmVolume;
	private float effectsVolume;
	// make singleton class uses holder idiom
	private AudioPlayer() {
		try {
			bgm=new Music("C:\\javaProject\\JavaModels\\sounds\\bgm.ogg");
			tick=new Sound("C:\\javaProject\\JavaModels\\sounds\\tick.ogg");
			teleport=new Sound("C:\\javaProject\\JavaModels\\sounds\\teleport.ogg");
			
			endingBgm=new Music("C:\\javaProject\\JavaModels\\sounds\\bgm.ogg");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bgmVolume=0.3f;
		effectsVolume=1.0f;
		bgm.play(1,bgmVolume);
		bgm.pause();
	}
	private static class SingletonAudio {
		private static final AudioPlayer instance = new AudioPlayer();
	} 
	public static AudioPlayer getInstance() {
		return SingletonAudio.instance;
	}
	public void bgmPlay(boolean playFlag) {
		if (playFlag == true ){
			bgm.resume();
		}
		else if (playFlag == false ) {
			bgm.pause();
		}
	}
	// effect play·Î ¹­±â
	public void tickPlayAtOnce() {
		tick.play(1,effectsVolume);
	}
	public void teleportSoundPlayAtOnce() {
		teleport.play(1,effectsVolume);
	}
	public void endingBgmPlay(boolean playFlag) {
		if (playFlag == true ){
			endingBgm.loop(1,bgmVolume);
		}
		else if (playFlag == false ) {
			endingBgm.stop();
		}
	}
	
	public float getBgmVolume() {
		return bgmVolume;
	}
	public void setBgmVolume(float volume) {
		this.bgmVolume=volume;
		bgm.setVolume(bgmVolume);
		endingBgm.setVolume(bgmVolume);
	}
}
