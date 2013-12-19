package me.dreamteam.tardis;

import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

public class Tardis {
	
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
	
	public static Entity Background;
	public static Entity Background2;
	public static Entity ship;
	public static int shipS = 0;

	public static ArrayList entities = new ArrayList();
	public static ArrayList enemies = new ArrayList();
	public static ArrayList backgroundImages = new ArrayList();
	public static ArrayList removeList = new ArrayList();
 
	public static double moveSpeed = 180;
	
	public static int SpriteLoc;
	public static int SpriteLoc2;
	public static int SpriteLoc3;
	
	public static int CurSprite = 1;
	public static double curY = 0;
	public static Random rSpriteLoc = new Random();

	/* Keyboard Listeners */
	
	public static boolean leftPressed = false;
	public static boolean rightPressed = false;
    
	/* Game Logic and Time */
	
	public static boolean logicRequiredThisLoop = false;
	public static boolean advanceLevel = false;
	public static int level = 1;
	public static long lastFrame;
	public static long finalTime = 0;
	public static String timeDisplay = "";
	public static String livesDisplay = "";
	public static int gameTime = 0;
	public static int gameLives = 3; 
	public static int timeMil;
	public static long lastTime;
	public static int tWait = 0;

}
