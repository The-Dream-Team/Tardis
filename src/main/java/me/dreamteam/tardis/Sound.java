package me.dreamteam.tardis;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {
    /*
    This class is reserved for sound development, whereby it will be a sound manager similar to the SpriteStore / Sprite class
     */
	
	public static final Sound sound1 = new Sound("/sound/theme_level1.wav");
	private AudioClip clip;

	public Sound(String filename){
		try{
			clip = Applet.newAudioClip(Sound.class.getResource(filename));
		}catch(Exception e){}
	}

	public void play(){
		try{
			new Thread(){
				public void run(){
					clip.play();
				}
			}.start();
		}catch(Exception ex){}
	}
}
