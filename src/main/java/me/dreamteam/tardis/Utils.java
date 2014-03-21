package me.dreamteam.tardis;

import javax.swing.*;
import java.net.URL;

public class Utils {

	/* 
     * Game Information
	 */

    static String gameName = "SUPER SPACESHIP ADVENTURE ";
    static String build = "Alpha ";
    static String version = "0.9.3";

	/*
     * Ship Names
	 */

    static String ship1Name = "P3N-15";
    static String ship2Name = "FALCON";
    static String ship3Name = "CICADA";
	
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

    static int weaponFS = 18; // Font size for Weapon display
    static int weaponIFS = 18; //Font size for number of ammo

    static String txtTime = "DISTANCE";
    static String txtLives = "LIVES";
    static String txtLevel = "LEVEL ";
    static String txtWeapon = "POWER UPS ";
	
	/*
	 * JFrame Options and Text
	 */

    // Loading (Splash)

    static String txtLoad = "Loading...";

    // Title Screen
    static String bPlay = "Play";
    static String bHowTo = "How to Play";
    static String bQuit = "Quit";
    static String bRestart = "Restart Game";
    static String bReturn = "Return to Game";
    static String txtTS = "Welcome to " + gameName;
    static String tsDialogTitle = "";

    // Username Entry
    static String txtUsernameEntry = "Enter your username";

    // Character Selection
    static String txtCS = "Choose your spaceship to begin:";

    // Game Over
    static String txtGO = "Game Over";
    static String txtTravelled = "You have travelled ";
    static String txtMetre = "0m";
    static String goDialogTitle = "";
    static String bPlayAgain = "Play Again";
    static String bHs = "High Scores";

    // Pause screen
    static String txtPS = "Game paused";

    // Icon URLS
    static URL splashURL = me.dreamteam.tardis.Game.class.getResource("/sprites/splashbackground.png");
    static URL iconURL = me.dreamteam.tardis.Game.class.getResource("/sprites/ship.png");
    static URL ship1URL = me.dreamteam.tardis.Game.class.getResource("/sprites/ship.png");
    static URL ship2URL = me.dreamteam.tardis.Game.class.getResource("/sprites/ship2.png");
    static URL ship3URL = me.dreamteam.tardis.Game.class.getResource("/sprites/ship3.png");

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
