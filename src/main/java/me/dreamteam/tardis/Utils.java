package me.dreamteam.tardis;

import javax.swing.*;
import java.net.URL;

public class Utils {

	/* 
	 * Game Information
	 */

    static String gameName = "SUPER SPACESHIP ADVENTURE ";
    static String build = "Alpha ";
    static String version = "0.8.9";
	
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

    static int splashFS = 24; // Loading screen

    static int timeFS = 18; // Font size for Time display
    static int timeIFS = 18; // Font size for timer

    static int livesFS = 18; // Font size for Lives display
    static int livesIFS = 18; // Font size for number of lives

    static int levelFS = 14; // Font size for Level display
    static int levelIFS = 14; //Font size for number of levels

    static String txtTime = "TIME";
    static String txtLives = "LIVES";
    static String txtLevel = "LEVEL ";
    static String txtWeapon = "WEAPON ";
	
	/*
	 * JFrame Options and Text
	 */

    static String lbk = "\n";

    // Loading (Splash)

    static String txtLoad = "Loading...";

    // Title Screen
    static String bPlay = "Play Game";
    static String bHowTo = "How to Play";
    static String bQuit = "Quit Game";
    static String bRestart = "Restart Game";
    static String bReturn = "Return to Game";
    static String txtTS = "Welcome to " + gameName;
    static String tsDialogTitle = "";

    // Character Selection
    static String txtCS = "Choose your spaceship to begin:";
    static String csDialogTitle = "Character Selection";

    // Game Over
    static String txtGO = "Game Over";
    static String txtSurvive = "You have survived for ";
    static String txtSeconds = " seconds";
    static String goDialogTitle = "";
    static String bPlayAgain = "Play Again";

    // Pause screen
    static String txtPS = "Game paused";

    // Icon URLS
    static URL splashURL = me.dreamteam.tardis.Game.class.getResource("/src/main/resources/sprites/splashbackground.png");
    static URL iconURL = me.dreamteam.tardis.Game.class.getResource("/src/main/resources/sprites/ship.png");
    static URL ship1URL = me.dreamteam.tardis.Game.class.getResource("/src/main/resources/sprites/ship.png");
    static URL ship2URL = me.dreamteam.tardis.Game.class.getResource("/src/main/resources/sprites/ship2.png");
    static URL ship3URL = me.dreamteam.tardis.Game.class.getResource("/src/main/resources/sprites/ship3.png");

    // Blank Icon
    static ImageIcon blankIcon = new ImageIcon();
	
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
