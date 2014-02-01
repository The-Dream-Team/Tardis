package me.dreamteam.tardis;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {

    // Themes
	public static final Sound soundTheme1 = new Sound("/sound/theme1.wav");

    // Explosions
    public static final Sound soundExplosion = new Sound("/sound/explosion.wav");

    public static final Sound soundGameOver = new Sound("/sound/gameover.wav");


	private AudioClip clip;

	public Sound(String filename){
		try{
			clip = Applet.newAudioClip(me.dreamteam.tardis.Game.class.getResource(filename));
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
