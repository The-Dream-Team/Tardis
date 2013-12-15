package me.dreamteam.tardis;

import java.net.URL;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Utils {
	
	/* 
	 * Game Information
	 */
	
	static String gameName = "Codename TARDIS ";
	static String build = "Alpha ";
	static String version = "0.5.2";
	
	/*
	 * Ship Names
	 */
	
	static String ship1Name = "P3N-15";
	static String ship2Name = "FALCON";
	static String ship3Name = "MOTH";
	
	/*
	 * gText Options
	 */
	
	static int fontSize = 18;  // Global Font Size
	
	static int timeFS = 18; // Font size for Time display
	static int timeIFS = 18; // Font size for timer
	
	static int livesFS = 18; // Font size for Lives display
	static int livesIFS = 18; // Font size for number of lives
	
	static int levelFS = 16; // Font size for Level display
	static int levelIFS = 16; //Font size for number of levels
	
	static String txtTime = "Time:";
	static String txtLives = "Lives:";
	static String txtLevel = "Level ";
	
	/*
	 * JFrame Options and Text
	 */
	
	static String lbk = "\n";
		
	// Title Screen
	static String bPlay = "Play Game";
	static String bQuit = "Quit Game";
	static String bRestart = "Restart Game";
	static String bReturn = "Return to Game";
	static String txtTS = "Welcome to " + gameName + version;
	static String tsDialogTitle = gameName;
	
	// Character Selection
	static String txtCS = "Choose your spaceship to begin:";
	static String csDialogTitle = "Character Selection";
	
	// Game Over
	static String txtGO = "Game Over";
	static String txtSurvive = "You have survived for ";
	static String txtSeconds = " seconds";
	static String goDialogTitle = "Game Over";
	static String bPlayAgain = "Play Again";
	
	// Pause screen
	static String txtPS = "Game paused";

    // Icon URLS
	static URL iconURL = me.dreamteam.tardis.Game.class.getResource("/sprites/ship.png");
	static URL ship1URL = me.dreamteam.tardis.Game.class.getResource("/sprites/ship.png");
	static URL ship2URL = me.dreamteam.tardis.Game.class.getResource("/sprites/ship2.png");
	static URL ship3URL = me.dreamteam.tardis.Game.class.getResource("/sprites/ship3.png");
	
	/*
	 * Utility Methods
	 */
	
	// Quit Game
    public static void quitGame() {
		System.exit(0);
    }
    
    // System look and feel
    public static void systemLF() {
	try {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (InstantiationException e) {
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		e.printStackTrace();
	} catch (UnsupportedLookAndFeelException e) {
		e.printStackTrace();
	} 
  }
}