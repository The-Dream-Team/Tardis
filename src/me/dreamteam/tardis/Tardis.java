package me.dreamteam.tardis;

import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public class Tardis {
	
	public static BufferStrategy strategy;
	// This provides hardware acceleration
	
	public static boolean debug = true;
	// Set debug on or off
	
	public static boolean isRunning = true;
	public static boolean gameStart = false;
	public static long lastLoopTime;
	
	public static Entity Background;
	public static Entity Background2;
	public static Entity ship;
	public static int shipS = 0;

	public static ArrayList entities = new ArrayList();
	public static ArrayList enemies = new ArrayList();
	public static ArrayList backgroundImages = new ArrayList();
	public static ArrayList removeList = new ArrayList();
 
	public static double moveSpeed = 180;
    
	public static boolean leftPressed = false;
	public static boolean rightPressed = false;
    
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
	
	public static int SpriteLoc;
	public static int SpriteLoc2;
	public static int SpriteLoc3;
	
	public static int tWait = 0;
	public static int CurSprite = 1;
	public static double curY = 0;
	
	public static int debugHits = 0;
	
	public static ImageIcon blankIcon = new ImageIcon();
	public static Random rSpriteLoc = new Random();

}
