package me.dreamteam.tardis;

import javax.swing.*;
import java.net.URL;

public class Utils {

	/* 
     * Game Information
	 */

    static String gameName = "SUPER SPACESHIP ADVENTURE ";
    static String build = "Beta ";
    static String version = "0.10.2";

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
     * GUI Text
     */

    // Buttons
    static String btnPlay = "Play Game >>";
    static String btnPlayGame = "Play Game";
    static String btnPrev = "<< Return to Previous Menu";
    static String btnPrevShort = "<<";
    static String btnHTP = "How to Play";
    static String btnAch = "Achievements & Unlocks";

    // Username
    static String txtUsernameEntry = "Enter your username";

    // Ship
    static String txtChooseShip = "Choose a ship from above";

    static String txtShip1Desc = "The PEN-15 is a medium speed ship and is manufactured by Surex Industries.\nThe ship can fire from the hull if ammo is present.";
    static String txtShip2Desc = "The FALCON is a fast ship and is manufactured by Hawk Industries.\nThe ship can fire from the the sides if ammo is present.";
    static String txtShip3Desc = "The CICADA is a slow ship and is manufactured by Anon Industries.\nThe ship can fire from the the sides if ammo is present.";

    static String txtShipLocked = "This ship is currently locked\nYou can unlock it through achievements";
    static String txtLocked = "LOCKED";

    // Validation warnings
    static String txtWarning = "Warning";
    static String txtWarningUsername = "Please enter an username";
    static String txtWarningUsernameChar = "Please enter an username that is at least 3 characters long";
    static String txtWarningShip = "Please choose a ship";

    // How to Play (WIP)
    static String txtHTPTitle = "                 How to Play";

    // Achievements
    static String txtAchTitle = "        Achievements & Unlocks ";

    static String txtAch1 = "Explorer";
    static String txtAch1Desc = "Travel a distance of 1000m";

    static String txtAch2 = "Voyager";
    static String txtAch2Desc = "Travel a distance of 5000m";

    static String txtAch3 = "TBA";
    static String txtAch3Desc = "TBA";

    // Website Dialog
    static String txtWebsite = "Website";
    static String txtWebsiteMsg = "The website will be opened within your web browser. \nDo you wish to continue?";
    static String urlWebsite = "http://the-dreamteam.co.uk";
    
    static String txtHighscoreMsg = "Highscore will be displayed on your web browser.";

    // Quit Game Dialog
    static String txtQuit = "Quit Game";
    static String txtQuitMsg = "Are you sure you want to quit?";


	
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
