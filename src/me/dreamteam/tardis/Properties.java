package me.dreamteam.tardis;

import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

public class Properties{
	
	/*
	 * IMPORTANT INFORMATION:
	 * This class is for the purpose of declaring game variables to be used in the main class (or in some cases, other classes)
	 */
	
	/* Hardware Acceleration */
	
	public BufferStrategy strategy;
	
	/* Debug */
	
	public static boolean debug = true;
	public static int debugHits = 0;
	
	/* Game Initilisation Variables */
	
	public boolean Running = true;
	public boolean gameStart = false;
	public long lastLoopTime;
	
	/* Entities */
	
	public Entity Background;
	public Entity Background2;
	public Entity ship;
	public int shipS = 0;

	public ArrayList entities = new ArrayList();
	public ArrayList enemies = new ArrayList();
	public ArrayList backgroundImages = new ArrayList();
	public ArrayList removeList = new ArrayList();
 
	public double moveSpeed = 180;
	
	public int SpriteLoc;
	public int SpriteLoc2;
	public int SpriteLoc3;
	
	public int CurSprite = 1;
	public double curY = 0;
	public Random rSpriteLoc = new Random();

	/* Keyboard Listeners */
	
	public boolean leftPressed = false;
	public boolean rightPressed = false;
    
	/* Game Logic and Time */
	
	public boolean advanceLevel = false;
	public int level = 1;
	public long lastFrame;
	public long finalTime = 0;
	public String timeDisplay = "";
	public String livesDisplay = "";
	public int weapon1 = 0;
	public int weapon2 = 0;
	public int weapon3 = 0;
	public String weaponDisplay = weapon1+"/"+weapon2+"/"+weapon3;
	public int gameTime = 0;
	public static int gameLives = 3; 
	public int timeMil;
	public long lastTime;
	public int tWait = 0;

}
