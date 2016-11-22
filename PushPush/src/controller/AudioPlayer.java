package controller;

import org.newdawn.slick.Music;
import org.newdawn.slick.Sound;

public class AudioPlayer {
	private Music bgm;
	private Sound tick;
	// make singleton class uses holder idiom
	private AudioPlayer() {
	}
	private static class SingletonAudio {
		private static final AudioPlayer instance = new AudioPlayer();
	} 
	public static AudioPlayer getInstance() {
		return SingletonAudio.instance;
	}
	
}
