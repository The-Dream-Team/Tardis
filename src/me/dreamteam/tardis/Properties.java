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
	
	public static BufferStrategy strategy;
	
	/* Debug */
	
	public static boolean debug = true;
	public static int debugHits = 0;
	
	/* Game Initilisation Variables */
	
	public static boolean isRunning = true;
	public static boolean gameStart = false;
	public static long lastLoopTime;
	
	/* Entities */
	
	public Entity Background;
	public Entity Background2;
	public Entity ship;
	public static int shipS = 0;

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
	
	public static boolean leftPressed = false;
	public static boolean rightPressed = false;
    
	/* Game Logic and Time */
	
	public static boolean logicRequiredThisLoop = false;
	public static boolean advanceLevel = false;
	public static int level = 1;
	public static long lastFrame;
	public long finalTime = 0;
	public static String timeDisplay = "";
	public static String livesDisplay = "";
	public static int gameTime = 0;
	public static int gameLives = 3; 
	public static int timeMil;
	public long lastTime;
	public int tWait = 0;

}
